package com.janus.deprem.ui.updatestatus;

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.SelectionTracker
import com.janus.deprem.R
import com.janus.deprem.data.Status
import com.janus.deprem.ui.common.Details

class StatusAdapter : Adapter<StatusViewHolder>() {
    private val statuses: Array<Status> = Status.values()
    lateinit var tracker: SelectionTracker<Long>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_status, parent, false) as TextView
        return StatusViewHolder(textView)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.details.position = position.toLong();
        with(holder.textView) {
            text = statuses[position].name
            if (tracker.isSelected(holder.details.selectionKey)) {
                isActivated = true
                setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            } else {
                isActivated = false
                setTextColor(ContextCompat.getColor(context, android.R.color.black))
            }
        }
    }

    override fun getItemCount() = statuses.size
}

class StatusViewHolder(val textView: TextView, val details: Details = Details()) :
    RecyclerView.ViewHolder(textView)