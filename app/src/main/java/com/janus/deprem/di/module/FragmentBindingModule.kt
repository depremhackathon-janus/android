package com.janus.deprem.di.module

import com.janus.deprem.ui.choose.ChooseActivityFragment
import com.janus.deprem.ui.currentstatus.CurrentStatusActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    internal abstract fun contributeChooseActivityFragment(): ChooseActivityFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCurrentStatusActivityFragment(): CurrentStatusActivityFragment
}
