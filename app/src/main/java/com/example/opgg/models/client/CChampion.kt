package com.example.opgg.models.client

import com.example.opgg.models.server.Champion

class CChampion(data: Champion?) {
    val imageUrl: String = data?.imageUrl ?: ""
    val key: String? = data?.key

    var games: Int = data?.games ?: 0
    var wins: Int = data?.wins ?: 0

    var winRate: Int = calcWinRate()
    var displayRate: String = displayRateFormat()

    fun update(pGames:Int, pWins: Int){
        games += pGames
        wins += pWins

        winRate = calcWinRate()
        displayRate = displayRateFormat()
    }

    private fun calcWinRate(): Int = ((wins/games.toFloat()) * 100).toInt()
    private fun displayRateFormat() = "$winRate%"
}