package com.janus.deprem.vm

import android.util.Log
import android.view.View
import androidx.recyclerview.selection.Selection
import com.janus.deprem.base.BaseViewModel
import javax.inject.Inject
import kotlin.math.pow

class UpdateStatusVM @Inject constructor() : BaseViewModel() {
    private var statuses = 0
    fun performUpdate(view: View) {
        Log.d("USVM", statuses.toString())
        // view.context!!.startActivityWithName(ChooseActivity::class.java)
    }

    fun updateSelection(selection: Selection<Long>?) {
        statuses = selection?.sumBy { 2.0.pow(it.toDouble()).toInt() } ?: 0
    }
}