package com.havebreak.poc

import android.os.Bundle
import android.text.Html
import android.text.TextUtils.replace
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.havebreak.poc.api.Api
import com.havebreak.poc.model.TicketFollowup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_ticket)

        val sessionToken = intent.getStringExtra("session_token")

        val apiService = Api().getApiService()

        val service = apiService.getTicketFollowup("15", sessionToken)

        CoroutineScope(IO).launch {
            service.enqueue(object : Callback<List<TicketFollowup>> {
                override fun onResponse(
                    call: Call<List<TicketFollowup>>,
                    response: Response<List<TicketFollowup>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("TESTEAPPP", response.body().toString())

                    }
                }

                override fun onFailure(call: Call<List<TicketFollowup>>, t: Throwable) {
                    //Do nothing
                }
            })
        }
    }
}