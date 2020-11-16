package com.havebreak.poc.api

import com.havebreak.poc.model.*
import retrofit2.Call
import retrofit2.http.*

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

    @POST("Ticket")
    @Headers("App-Token: 3N9cWoE2bfWagGLQonEZHY1kPtHRCqOrRG61HnpS")
    fun createTicket(
        //@Query("session-token") sessionToken: String,
        @Header("Session-Token") sessionToken: String,
        @Body ticket: Ticket
    ): Call<Unit>

    @GET("Ticket/{ticketId}/TicketFollowup")
    @Headers("App-Token: 3N9cWoE2bfWagGLQonEZHY1kPtHRCqOrRG61HnpS")
    fun getTicketFollowup(
        @Path("ticketId") ticketId: String,
        @Query("session_token") sessionToken: String
    ): Call<List<TicketFollowup>>

    @GET("Ticket/{ticketId}/ITILSolution")
    @Headers("App-Token: 3N9cWoE2bfWagGLQonEZHY1kPtHRCqOrRG61HnpS")
    fun getTicketSupportSolution(
        @Path("ticketId") ticketId: String,
        @Query("session_token") sessionToken: String
    ): Call<List<TicketSupportSolution>>

    @GET("Ticket/{ticketId}")
    @Headers("App-Token: 3N9cWoE2bfWagGLQonEZHY1kPtHRCqOrRG61HnpS")
    fun getTicket(
        @Path("ticketId") ticketId: String,
        @Query("session_token") sessionToken: String
    ): Call<TicketData>
}