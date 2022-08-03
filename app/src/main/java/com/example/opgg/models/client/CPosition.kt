package com.example.opgg.models.client

import android.graphics.drawable.Drawable
import com.example.opgg.R
import com.example.opgg.models.server.Champion
import com.example.opgg.models.server.Position

class CPosition(data: Position?) {
    val position: String = data?.position ?: ""

    var games: Int = data?.games ?: 0
    var wins: Int = data?.wins ?: 0

    var winRate: Int = calcWinRate()
    var displayRate: String = displayRateFormat()

    val drawable: Int? = when(position){
        "TOP" -> R.drawable.ic_icon_lol_top
        "MID" -> R.drawable.ic_icon_lol_mid
        "BOT" -> R.drawable.ic_icon_lol_bot
        "SUP" -> R.drawable.ic_icon_lol_sup
        "JNG" -> R.drawable.ic_icon_lol_jng
        else -> null
    }

    fun update(pGames:Int, pWins: Int){
        games += pGames
        wins += pWins

        winRate = calcWinRate()
        displayRate = displayRateFormat()
    }

    private fun calcWinRate(): Int = ((wins/games.toFloat()) * 100).toInt()
    private fun displayRateFormat() = "$winRate%"
}