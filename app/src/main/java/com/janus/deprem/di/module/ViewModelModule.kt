package com.janus.deprem.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.janus.deprem.base.ViewModelFactory
import com.janus.deprem.di.mapkey.ViewModelKey
import com.janus.deprem.vm.ChooseVM
import com.janus.deprem.vm.CurrentStatusVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChooseVM::class)
    internal abstract fun bindChooseVM(viewModel: ChooseVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrentStatusVM::class)
    internal abstract fun bindCurrentStatusVM(viewModel: CurrentStatusVM): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
