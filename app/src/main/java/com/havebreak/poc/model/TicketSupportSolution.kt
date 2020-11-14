package com.havebreak.poc.model

import com.google.gson.annotations.SerializedName

data class TicketSupportSolution(
    @SerializedName("id") val id: String,
    @SerializedName("content") val content: String,
    @SerializedName("date_creation") val dateCreation: String,
    @SerializedName("date_mod") val dateModification: String? = null,
    @SerializedName("date_approval") val dateApproval: String? = null,
    @SerializedName("status") val status: String? = null
)