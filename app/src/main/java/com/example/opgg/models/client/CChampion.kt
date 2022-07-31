package com.example.opgg.models.client

import com.example.opgg.models.server.Champion
import com.example.opgg.models.server.Summoner

class CChampion(data: Champion?) {
    val imageUrl: String = data?.imageUrl ?: ""

    var winsCount: Int = 0
    var gamesCount: Int = 0
    var winsRate: Int = 0
    var displayRate: String = ""

    fun setWinsRate(isWin: Boolean){
        gamesCount++
        if(isWin) winsCount++

        winsRate = ((winsCount.toFloat()/gamesCount.toFloat()) * 100).toInt()
        displayRate = "$winsRate%"
    }
}