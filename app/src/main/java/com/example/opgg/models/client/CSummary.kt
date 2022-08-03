package com.example.opgg.models.client

import com.example.opgg.models.server.Summary
import com.example.opgg.views.adapters.IMainListItem

class CSummary : IMainListItem {
    val champions: MutableList<CChampion> = mutableListOf()
    val positions: MutableList<CPosition> = mutableListOf()

    var wins: Int = 0
    var losses: Int = 0
    var games: Int = 0

    var kills: Float = 0F
    var deaths: Float = 0F
    var assists: Float = 0F
    var displayStats: String = ""
    var kda: String = ""

    var mostChampions: List<CChampion> = listOf()
    var mostPosition: CPosition? = null

    fun update(
        summary: Summary?,
        pChampions: List<CChampion>,
        pPositions: List<CPosition>,
        pGames: List<CGame>,
    ){
        summary?.run {
            this@CSummary.wins += wins ?: 0
            this@CSummary.losses += losses ?:0
            this@CSummary.kills += kills ?: 0
            this@CSummary.deaths += deaths ?: 0
            this@CSummary.assists += assists ?: 0
        }

        games = wins + losses
        val killAvg: Float = kills / games
        val deathAvg: Float = deaths / games
        val assistAvg: Float = assists / games

        displayStats = String.format("%.1f / %.1f / %.1f", killAvg, deathAvg, assistAvg)

        val sumContributionKillRate = pGames.map { game ->
            game.contributionKillRate
                .replace("%", "")
                .trim()
                .toIntOrNull()
        }.sumOf { rate ->
            rate ?: 0
        }

        kda = String.format("%.2f:1 (%d%%)", (kills + assists)/deaths, sumContributionKillRate / games)

        pChampions.forEach { champ ->
            val existChamp = champions.find { existChamp -> existChamp.key == champ.key }
            if(existChamp == null){
                champions.add(champ)
            }else{
                existChamp.update(champ.games, champ.wins)
            }
        }

        val orderedChampList = champions.sortedBy { champ -> champ.winRate }.reversed()
        mostChampions =
            if (orderedChampList.isEmpty()) {
                emptyList()
            }else if(orderedChampList.size < 2){
                orderedChampList.subList(0, orderedChampList.size).toList()
            }else {
                orderedChampList.subList(0, 1).toList()
            }

        pPositions.forEach { position ->
            val existPosition = positions.find { existPosition -> existPosition.position == position.position }
            if(existPosition == null){
                positions.add(position)
            }else{
                existPosition.update(position.games, position.wins)
            }
        }

        val orderedPositionList = positions.sortedBy { position -> position.winRate }.reversed()
        if(orderedChampList.isNotEmpty()){
            mostPosition = orderedPositionList.get(0)
        }
    }
}