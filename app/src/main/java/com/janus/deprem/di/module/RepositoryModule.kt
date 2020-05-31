package com.janus.deprem.di.module

import android.content.SharedPreferences
import com.janus.deprem.di.scope.AppScope
import com.janus.deprem.repo.TokenRepository
import com.janus.deprem.repo.LocationRepository
import dagger.Module
import dagger.Provides

@Module(includes = [SharedPrefUtilModule::class])
class RepositoryModule {

    @AppScope
    @Provides
    internal fun provideTokenRepository(sharedPreferences: SharedPreferences): TokenRepository {
        return TokenRepository(sharedPreferences)
    }

    @AppScope
    @Provides
    internal fun provideLocationRepository(): LocationRepository {
        return LocationRepository()
    }
}
