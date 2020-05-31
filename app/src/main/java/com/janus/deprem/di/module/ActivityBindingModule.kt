package com.janus.deprem.di.module

import com.janus.deprem.ui.choose.ChooseActivity
import com.janus.deprem.ui.currentstatus.CurrentStatusActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    internal abstract fun contributeChooseActivity(): ChooseActivity

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    internal abstract fun contributeCurrentStatusActivity(): CurrentStatusActivity
}
