package com.havebreak.poc.model

import com.google.gson.annotations.SerializedName

data class TicketFollowup(
    @SerializedName("id") val id: String? = null,
    @SerializedName("tickets_id") val tickectsId: String,
    @SerializedName("date") val date: String? = null,
    @SerializedName("content") val content: String,
    @SerializedName("add_reopen") val addReopen: String? = null
)