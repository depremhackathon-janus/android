package com.janus.deprem.vm

import android.view.View
import com.janus.deprem.base.BaseViewModel
import com.janus.deprem.ui.choose.ChooseActivity
import com.janus.deprem.util.startActivityWithName
import javax.inject.Inject


class CurrentStatusVM @Inject constructor() : BaseViewModel() {

    fun performMap(view: View) {
        view.context!!.startActivityWithName(ChooseActivity::class.java)
    }

    fun performUpdateStatus(view: View) {
        view.context!!.startActivityWithName(ChooseActivity::class.java)
    }

}
