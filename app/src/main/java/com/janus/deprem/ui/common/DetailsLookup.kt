package com.janus.deprem.ui.common

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import com.janus.deprem.ui.updatestatus.StatusViewHolder

class DetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        recyclerView.findChildViewUnder(e.x, e.y)?.let {
            return (recyclerView.getChildViewHolder(it) as? StatusViewHolder)?.details
        }
        return null
    }

}