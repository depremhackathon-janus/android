package com.janus.deprem.di.module

import android.content.Context
import android.os.ConditionVariable
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.janus.deprem.BuildConfig
import com.janus.deprem.di.scope.AppScope
import com.janus.deprem.remote.*
import com.janus.deprem.remote.ApiConstants.API_URL
import com.janus.deprem.repo.TokenRepository
import com.janus.deprem.ui.currentstatus.CurrentStatusActivity
import com.janus.deprem.util.startActivityWithName
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean


@Module(includes = [RepositoryModule::class])
class NetworkModule {

    companion object {
        private const val CLIENT_TIME_OUT = 5 * 60L
    }

    @AppScope
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
        authenticator: Authenticator,
        okHttpClientHolder: OkHttpClientHolder
    ): OkHttpClient {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .authenticator(authenticator)
            .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .build()
        okHttpClientHolder.client = client
        return client
    }

    @AppScope
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): NetworkService {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NetworkService::class.java)
    }

    @AppScope
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    @AppScope
    @Provides
    fun provideHeaderInterceptor(tokenRepository: TokenRepository, lock: ConditionVariable) = Interceptor {
        val original = it.request()
        val oldUrl = original.url()

        val newBuilder = oldUrl.newBuilder()
        val url = newBuilder.build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder().url(url)

        val request = when {
            oldUrl.isRefresh() -> {
                requestBuilder.addTokenIfAvailable(true, tokenRepository).build()
            }
            oldUrl.isAuthorized() -> {
                lock.block()
                requestBuilder.addTokenIfAvailable(false, tokenRepository).build()
            }
            else -> requestBuilder.build()
        }
        it.proceed(request)
    }


    @AppScope
    @Provides
    fun provideNetworkHandlerHolder(): NetworkHandlerHolder {
        return NetworkHandlerHolder()
    }

    @AppScope
    @Provides
    fun provideOkHttpClientHolder(): OkHttpClientHolder {
        return OkHttpClientHolder()
    }

    @AppScope
    @Provides
    fun provideAuthenticator(
        isRefreshing: AtomicBoolean,
        lock: ConditionVariable,
        tokenRepository: TokenRepository,
        networkHandlerHolder: NetworkHandlerHolder,
        okHttpClientHolder: OkHttpClientHolder,
        context: Context
    ) = Authenticator { _: Route, response: Response ->
        if (isRefreshing.compareAndSet(false, true)) {
            lock.close()
            networkHandlerHolder
                .networkHandler
                .refreshToken()
                .subscribe {
                    when (it) {
                        is DataHolder.Success -> {
                            tokenRepository.saveToken(it)
                            clearLocks(lock, isRefreshing)
                        }
                        is DataHolder.Error -> {
                            resetApp(okHttpClientHolder.client, tokenRepository, context)
                            clearLocks(lock, isRefreshing)
                        }
                    }
                }
        } else if (response.request().url().isRefresh()) {
            resetApp(okHttpClientHolder.client, tokenRepository, context)
            clearLocks(lock, isRefreshing)
        }
        lock.block()
        response
            .request()
            .newBuilder()
            .header("Authorization", "Bearer ${tokenRepository.getAccessToken()}")
            .build()
    }

    private fun clearLocks(lock: ConditionVariable, isRefreshing: AtomicBoolean) {
        lock.open()
        isRefreshing.set(false)
    }

    private fun resetApp(okHttpClient: OkHttpClient, tokenRepository: TokenRepository, context: Context) {
        okHttpClient.dispatcher().cancelAll()
        tokenRepository.clearTokens()
        context.startActivityWithName(CurrentStatusActivity::class.java, true)
    }

    @AppScope
    @Provides
    fun provideLock() = ConditionVariable(true)

    @AppScope
    @Provides
    fun provideIsRefreshing() = AtomicBoolean(false)
}


