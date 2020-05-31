package com.janus.deprem.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<DB : ViewDataBinding> : DaggerAppCompatActivity() {
    val TAG = javaClass.simpleName

    lateinit var binding: DB
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    fun loadFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if (addToBackStack) {
            ft.addToBackStack("")
        }
        ft.replace(containerId, fragment)
        ft.commit()
    }

}
