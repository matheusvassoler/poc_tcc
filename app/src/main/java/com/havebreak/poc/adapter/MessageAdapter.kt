package com.havebreak.poc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.havebreak.poc.R
import com.havebreak.poc.ui.TicketDataUI
import com.havebreak.poc.ui.TicketFollowupUI
import com.havebreak.poc.ui.TicketSupportSolutionUI
import com.havebreak.poc.ui.TicketUI
import kotlinx.android.synthetic.main.attendant_message.view.*
import kotlinx.android.synthetic.main.user_message.view.*

class MessageAdapter(
    private val ticketList: List<TicketUI>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TicketDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userMessage:TextView = itemView.userMessageTextView
    }

    class TicketFollowupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userMessage:TextView = itemView.userMessageTextView
    }

    class TicketSupportSolutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val attendantMessage:TextView = itemView.attendantMessageTextView
    }

    private fun getTicketDataViewHolder(parent: ViewGroup): TicketDataViewHolder {
        return TicketDataViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.user_message, parent, false)
        )
    }

    private fun getTicketFollowupViewHolder(parent: ViewGroup): TicketFollowupViewHolder {
        return TicketFollowupViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.user_message, parent, false)
        )
    }

    private fun getTicketSupportSolutionViewHoler(parent: ViewGroup): TicketSupportSolutionViewHolder {
        return TicketSupportSolutionViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.attendant_message, parent, false)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TICKET_FOLLOWUP -> getTicketFollowupViewHolder(parent)
            TICKET_SUPPORT_SOLUTION -> getTicketSupportSolutionViewHoler(parent)
            else -> getTicketDataViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TicketDataViewHolder -> {
                val ticketData = ticketList[position] as TicketDataUI
                holder.userMessage.text = ticketData.content
            }
            is TicketFollowupViewHolder -> {
                val ticketFollowup = ticketList[position] as TicketFollowupUI
                holder.userMessage.text = ticketFollowup.content
            }
            is TicketSupportSolutionViewHolder -> {
                val ticketSupportSolution = ticketList[position] as TicketSupportSolutionUI
                holder.attendantMessage.text = ticketSupportSolution.content
            }
        }
    }

    override fun getItemCount(): Int = ticketList.count()

    override fun getItemViewType(position: Int): Int {
       return when {
           ticketList[position] is TicketFollowupUI -> TICKET_FOLLOWUP
           ticketList[position] is TicketSupportSolutionUI -> TICKET_SUPPORT_SOLUTION
           else -> TICKET_DATA
       }
    }

    companion object ViewTypes {
        const val TICKET_DATA = 0
        const val TICKET_FOLLOWUP = 1
        const val TICKET_SUPPORT_SOLUTION = 2
    }
}