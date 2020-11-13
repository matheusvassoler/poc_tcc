package com.havebreak.poc.model

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("input") val ticketData: TicketData
)