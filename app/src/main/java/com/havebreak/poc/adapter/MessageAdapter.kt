package com.havebreak.poc.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.havebreak.poc.model.TicketData
import com.havebreak.poc.model.TicketFollowup

class MessageAdapter(
    private val ticketData: TicketData,
    private val ticketFollowupList: List<TicketFollowup>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}