package com.example.opgg.models.client

import com.example.opgg.views.adapters.IMainListItem

class CSummary(data: List<CGame>) : IMainListItem {
    val totalGames: Int = data.size
    val wins: Int = data.filter { game -> game.isWin }.size
    val losses: Int = totalGames - wins
    val mostChampions: MutableList<CChampion> = mutableListOf()

    val killAvg: Float
    val assistAvg: Float
    val deathAvg: Float
    val displayStats: String
    val killAssistRate: String
    val contributionForKillRate: String = "(50%)" //TODO

    init {
        killAvg = data.sumOf { game -> game.kills }.toFloat() / totalGames
        assistAvg = data.sumOf { game -> game.assists }.toFloat() / totalGames
        deathAvg = data.sumOf { game -> game.deaths }.toFloat() / totalGames
        displayStats = String.format("%.1f / %.1f / %.1f", killAvg, assistAvg, deathAvg)
        killAssistRate = String.format("%.2f", (killAvg + assistAvg)/deathAvg)

        data.forEach { game ->
            if(mostChampions.contains(game.champion)){
                val existChampion = mostChampions.firstOrNull {
                        champion -> champion.imageUrl == game.champion.imageUrl
                }

                existChampion?.setWinsRate(game.isWin)
            }else{
                mostChampions.add(game.champion.apply { setWinsRate(game.isWin) })
            }
        }
    }
}