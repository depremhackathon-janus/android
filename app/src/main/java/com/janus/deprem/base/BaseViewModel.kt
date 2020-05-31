package com.janus.deprem.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.janus.deprem.util.Status
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    val TAG = javaClass.simpleName
    private val disposables = CompositeDisposable()
    val status = MutableLiveData<Status>()

    init {
        status.value = Status.NOT_STARTED
    }

    fun add(d: Disposable) {
        disposables.add(d)
    }

    override fun onCleared() {
        dispose()
        super.onCleared()
    }

    fun dispose() {
        disposables.clear()
    }
}