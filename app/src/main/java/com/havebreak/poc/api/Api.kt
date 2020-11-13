package com.havebreak.poc.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.18/glpi/apirest.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getApiService(): ApiService = apiService
}