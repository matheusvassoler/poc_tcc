package com.havebreak.poc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.havebreak.poc.adapter.MessageAdapter
import com.havebreak.poc.api.Api
import com.havebreak.poc.model.TicketData
import com.havebreak.poc.model.TicketFollowup
import com.havebreak.poc.model.TicketSupportSolution
import com.havebreak.poc.ui.TicketDataUI
import com.havebreak.poc.ui.TicketFollowupUI
import com.havebreak.poc.ui.TicketSupportSolutionUI
import com.havebreak.poc.ui.TicketUI
import kotlinx.android.synthetic.main.activity_follow_ticket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main


class FollowTicketActivity : AppCompatActivity() {

    private lateinit var ticketFollowupList:MutableList<TicketFollowupUI>
    private lateinit var ticketSupportSolutionList:MutableList<TicketSupportSolutionUI>
    private lateinit var ticketDataList:TicketDataUI
    private var ticketUI:MutableList<TicketUI> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_ticket)

        val sessionToken = intent.getStringExtra("session_token")

        var apiService = Api().getApiService()

        var service = apiService.getTicketFollowup("15", sessionToken)

        runBlocking {

            val a = async(IO) {
                val ticketFollowupResponse =  service.execute()
                val ticketFollowup = ticketFollowupResponse.body()
                Log.i("TESTEAPP", ticketFollowup.toString())
                ticketFollowup!!.forEach {
                    ticketUI.add(
                        TicketFollowupUI(
                            id = it.id!!,
                            ticketsId = it.tickectsId,
                            date = it.date!!,
                            content = it.content.replace("&lt;", "").replace("p&gt;", "").replace("/", "")
                        )
                    )
                }
                //ticketFollowupList = ticketFollowup!!
            }

            val b = async(IO) {
                val serviceTicketData = apiService.getTicket("15", sessionToken)

                val ticketDataResponse = serviceTicketData.execute()
                val ticketData = ticketDataResponse.body()
                Log.i("TESTEAPP", ticketData.toString())

                ticketUI.add(TicketDataUI(
                    id = ticketData?.id!!,
                    name = ticketData.name!!,
                    content = ticketData.content.replace("&lt;", "").replace("p&gt;", "").replace("/", "")
                ))
                //ticketDataList = ticketData!!
            }

            val c = async(IO) {
                val serviceTicketSupportSolution = apiService.getTicketSupportSolution("15", sessionToken)

                val ticketSupportSolutionResponse = serviceTicketSupportSolution.execute()
                val ticketSolution = ticketSupportSolutionResponse.body()
                Log.i("TESTEAPP", ticketSolution.toString())

                ticketSolution?.forEach {
                    ticketUI.add(
                        TicketSupportSolutionUI(
                            id = it.id,
                            content = it.content.replace("&lt;", "").replace("p&gt;", "").replace("/", ""),
                            dateCreation = it.dateCreation,
                            dateModification = it.dateModification,
                            dateApproval = it.dateApproval,
                            status = it.status
                        )
                    )
                }
                //ticketSupportSolutionList = ticketSolution!!
            }

            a.await()
            b.await()
            c.await()
        }

        runBlocking {
            val ticketUIList:List<TicketUI> = ticketUI
            val c = async(Dispatchers.Default) {
                messageList.apply {
                    layoutManager = LinearLayoutManager(this@FollowTicketActivity)
                    adapter = MessageAdapter(ticketUIList, this@FollowTicketActivity)
                }
            }
            c.await()
        }
    }
}