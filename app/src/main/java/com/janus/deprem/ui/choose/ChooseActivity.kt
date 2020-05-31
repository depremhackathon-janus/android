package com.janus.deprem.ui.choose

import android.os.Bundle
import com.janus.deprem.R
import com.janus.deprem.base.BaseActivity
import com.janus.deprem.databinding.ActivityChooseBinding

class ChooseActivity : BaseActivity<ActivityChooseBinding>() {
    override val layoutRes: Int = R.layout.activity_choose
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) { // to prevent recreation of fragment, vm, etc..
            loadFragment(R.id.container, ChooseActivityFragment(), false)
        }
    }
}
