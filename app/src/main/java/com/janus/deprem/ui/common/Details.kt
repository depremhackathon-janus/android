package com.janus.deprem.ui.common

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup

class Details : ItemDetailsLookup.ItemDetails<Long>() {
    var position: Long = 0
    override fun getPosition() = position.toInt()
    override fun getSelectionKey() = position
    override fun inSelectionHotspot(e: MotionEvent) = true
}