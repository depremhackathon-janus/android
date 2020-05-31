package com.janus.deprem.di.module

import com.janus.deprem.di.scope.AppScope
import com.janus.deprem.remote.NetworkHandler
import com.janus.deprem.repo.TokenRepository
import com.janus.deprem.repo.LocationRepository
import com.janus.deprem.usecase.LoginUseCase
import com.janus.deprem.usecase.LocationUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class UsecaseModule {
    @AppScope
    @Provides
    internal fun provideLoginUseCase(
        networkHandler: NetworkHandler,
        tokenRepository: TokenRepository
    ): LoginUseCase {
        return LoginUseCase(networkHandler, tokenRepository)
    }

    @AppScope
    @Provides
    internal fun provideLocationUseCase(
        networkHandler: NetworkHandler,
        tokenRepository: LocationRepository
    ): LocationUseCase {
        return LocationUseCase(networkHandler, tokenRepository)
    }
}
