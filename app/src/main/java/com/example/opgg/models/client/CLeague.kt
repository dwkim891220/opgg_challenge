package com.example.opgg.models.client

import com.example.opgg.models.server.League

class CLeague(data: League?) {
    val name: String = data?.name ?: ""
    val tier: String = data?.tier ?: ""
    val imageUrl: String = data?.imageUrl ?: ""
    val lp: String = "${data?.lp?.toString()} LP"

    val displayWinLosses: String

    init {
        displayWinLosses =
            if(data?.wins != null && data.losses != null){
                val rate = (data.wins.toFloat() / (data.wins + data.losses).toFloat())*100
                "${data.wins}승 ${data.losses}패 (${rate.toInt()}%)"
            }else{
                ""
            }
    }
}