package com.havebreak.poc.api

import com.havebreak.poc.model.Login
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("initSession")
    @Headers("App-Token: 3N9cWoE2bfWagGLQonEZHY1kPtHRCqOrRG61HnpS")
    fun doLogin(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<Login>

    @GET("killSession")
    @Headers("App-Token: 3N9cWoE2bfWagGLQonEZHY1kPtHRCqOrRG61HnpS")
    fun finishSession(
        @Header("Session-Token") sessionToken: String
    ): Call<Unit>
}