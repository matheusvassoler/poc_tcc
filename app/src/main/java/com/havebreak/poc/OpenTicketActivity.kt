package com.havebreak.poc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.havebreak.poc.api.Api
import com.havebreak.poc.model.Ticket
import com.havebreak.poc.model.TicketData
import kotlinx.android.synthetic.main.activity_open_ticket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_ticket)

        val sessionToken = intent.getStringExtra("session_token")

        button.setOnClickListener {
            val studentId = editTextTextPersonName.text.toString()
            val fullName = editTextTextPersonName2.text.toString()
            val email = editTextTextPersonName3.text.toString()
            val phone = editTextTextPersonName4.text.toString()
            val problem = editTextTextMultiLine.text.toString()

            val apiService = Api().getApiService()

            val cont = "Prontuario: $studentId \n Nome completo: $fullName \n E-mail: $email \n Telefone: $phone \n Problema: $problem"

            val ticketData = TicketData(
                name = "Email não responde",
                content = cont,
                type = "2",
                status = "1"
            )

            val ticket = Ticket(ticketData)

            CoroutineScope(IO).launch {
                val result = apiService.createTicket(sessionToken, ticket)

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
    }
}