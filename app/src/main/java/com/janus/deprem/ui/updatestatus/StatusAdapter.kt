package com.janus.deprem.ui.updatestatus

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.SelectionTracker
import com.janus.deprem.R
import com.janus.deprem.data.Status
import com.janus.deprem.ui.common.Details

class StatusAdapter : Adapter<StatusViewHolder>() {
    private val statuses: Array<Status> = Status.values()
    lateinit var tracker: SelectionTracker<Long>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StatusViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_status, parent, false)
    )

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.details.position = position.toLong()
        with(holder.root.findViewById<CardView>(R.id.cv_item)) {
            val name = findViewById<TextView>(R.id.tv_name)
            name.text = statuses[position].description
            if (tracker.isSelected(holder.details.selectionKey)) {
                isActivated = true
                name.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
            } else {
                isActivated = false
                name.setTextColor(ContextCompat.getColor(context, android.R.color.black))
                setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }

    override fun getItemCount() = statuses.size
}

class StatusViewHolder(val root: View, val details: Details = Details()) :
    RecyclerView.ViewHolder(root)