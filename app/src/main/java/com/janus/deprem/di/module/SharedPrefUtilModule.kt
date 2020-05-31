package com.janus.deprem.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.janus.deprem.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class SharedPrefUtilModule {

    @Provides
    @AppScope
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @AppScope
    fun provideSharedPref(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}