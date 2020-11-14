package com.havebreak.poc.ui

data class TicketFollowupUI(
    val id: String,
    val ticketsId: String,
    val date: String,
    val content: String
): TicketUI()