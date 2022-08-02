package com.example.opgg.models.client

import com.example.opgg.views.adapters.IMainListItem

class CSummary : IMainListItem {
    var games: MutableList<CGame> = mutableListOf()
    val mostChampions: MutableList<CChampion> = mutableListOf()

    var wins: Int = 0
    var losses: Int = 0

    var killAvg: Float = 0F
    var deathAvg: Float = 0F
    var assistAvg: Float = 0F
    var displayStats: String = ""
    var kda: String = ""

    fun update(newData: List<CGame>){
        games.addAll(newData)

        killAvg = games.sumOf { game -> game.kills }.toFloat() / games.size
        deathAvg = games.sumOf { game -> game.deaths }.toFloat() / games.size
        assistAvg = games.sumOf { game -> game.assists }.toFloat() / games.size
        displayStats = String.format("%.1f / %.1f / %.1f", killAvg, deathAvg, assistAvg)

        val sumContributionKillRate = games.map { game ->
            game.contributionKillRate
                .replace("%", "")
                .trim()
                .toIntOrNull()
        }.sumOf { rate ->
            rate ?: 0
        }

        kda = String.format("%.2f:1 (%d%%)", (killAvg + assistAvg)/deathAvg, sumContributionKillRate / games.size)

        games.forEach { game ->
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