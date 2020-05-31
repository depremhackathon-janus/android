package com.janus.deprem.vm

import android.view.View
import androidx.recyclerview.selection.Selection
import com.janus.deprem.base.BaseViewModel
import com.janus.deprem.data.PersonInfo
import com.janus.deprem.util.sendSMS
import javax.inject.Inject
import kotlin.math.pow

class UpdateStatusVM @Inject constructor() : BaseViewModel() {
    private var statuses = 0
    fun performUpdate(view: View) = view.context!!.sendSMS(PersonInfo(5558054761, statuses))

    fun updateSelection(selection: Selection<Long>?) {
        statuses = selection?.sumBy { 2.0.pow(it.toDouble()).toInt() } ?: 0
    }
}