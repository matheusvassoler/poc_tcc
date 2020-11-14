package com.havebreak.poc.model

import com.google.gson.annotations.SerializedName

data class TicketData (
    @SerializedName("name") val name: String,
    @SerializedName("content") val content: String,
    @SerializedName("type") val type: String,
    @SerializedName("status") val status: String,
    @SerializedName("date") val date: String? = null,
    @SerializedName("closedate") val closeDate: String? = null,
    @SerializedName("solvedate") val solveData: String? = null,
    @SerializedName("id") val id: String? = null
)