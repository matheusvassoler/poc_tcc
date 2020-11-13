package com.havebreak.poc.model

import com.google.gson.annotations.SerializedName

data class TicketData (
    @SerializedName("name") val name: String,
    @SerializedName("content") val content: String,
    @SerializedName("type") val type: String,
    @SerializedName("status") val status: String
)