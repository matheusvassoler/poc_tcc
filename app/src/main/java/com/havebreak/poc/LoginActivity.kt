package com.havebreak.poc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.havebreak.poc.api.Api
import com.havebreak.poc.model.Login
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            val apiService = Api().getApiService()

            CoroutineScope(IO).launch {
                val result = apiService.doLogin(usernameField.text.toString(), passwordField.text.toString())

                result.enqueue(object : Callback<Login> {
                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        //Do nothing
                        Log.i("APP-TESTE", "TESTE")
                    }

                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if(response.isSuccessful) {
                            val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                            intent.putExtra("session_token", response.body()?.sessionToken)
                            startActivity(intent)
                        }
                    }
                })
            }
        }
    }
}