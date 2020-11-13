package com.havebreak.poc.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("session_token") val sessionToken: String
)