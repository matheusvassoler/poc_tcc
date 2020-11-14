package com.havebreak.poc.ui

data class TicketSupportSolutionUI(
    val id: String,
    val content: String,
    val dateCreation: String, 
    val dateModification: String? = null,
    val dateApproval: String? = null,
    val status: String? = null
) : TicketUI()