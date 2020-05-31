package com.janus.deprem.ui.currentstatus

import android.os.Bundle
import com.janus.deprem.R
import com.janus.deprem.base.BaseActivity
import com.janus.deprem.databinding.ActivityCurrentStatusBinding

class CurrentStatusActivity : BaseActivity<ActivityCurrentStatusBinding>() {
    override val layoutRes: Int = R.layout.activity_current_status
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) { // to prevent recreation of fragment, vm, etc..
            loadFragment(R.id.container, CurrentStatusActivityFragment(), false)
        }
    }
}