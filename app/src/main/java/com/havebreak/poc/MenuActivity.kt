package com.havebreak.poc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.havebreak.poc.api.Api
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val sessionToken = intent.getStringExtra("session_token")

        finishSessionButton.setOnClickListener {
            val apiService = Api().getApiService()

            CoroutineScope(IO).launch {
                val result = apiService.finishSession(sessionToken)

                result.enqueue(object : Callback<Unit> {
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        //Do nothing
                    }

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful) {
                            finish()
                        }
                    }
                })
            }
        }

        openTicketButton.setOnClickListener {
            Intent(this, OpenTicketActivity::class.java).apply {
                putExtra("session_token", sessionToken)
                startActivity(this)
            }
        }

        followTicketButton.setOnClickListener {
            Intent(this, FollowTicketActivity::class.java).apply {
                putExtra("session_token", sessionToken)
                startActivity(this)
            }
        }
    }
}