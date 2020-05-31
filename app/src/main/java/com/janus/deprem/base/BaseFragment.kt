package com.janus.deprem.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.OnRebindCallback
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.janus.deprem.BR
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding> : DaggerFragment() {
    val TAG = javaClass.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM
    protected lateinit var binding: DB

    abstract val getLayoutId: Int
    abstract val viewModelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.setLifecycleOwner(this)
        binding.setVariable(BR.vm, viewModel)
        binding.addOnRebindCallback(object : OnRebindCallback<ViewDataBinding>() {
            override fun onPreBind(binding: ViewDataBinding?): Boolean {
                val viewGroup = binding?.root as ViewGroup
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(viewGroup)
                } else {
                    android.support.transition.TransitionManager.beginDelayedTransition(viewGroup)
                }
                return super.onPreBind(binding)
            }
        })
        return binding.root
    }

    fun loadFragment(contanerId: Int, fragment: Fragment, fm: FragmentManager?, addToBackStack: Boolean) {
        val ft = fm?.beginTransaction()
        if (addToBackStack) {
            ft?.addToBackStack("")
        }
        ft?.replace(contanerId, fragment)
        ft?.commit()
    }

}