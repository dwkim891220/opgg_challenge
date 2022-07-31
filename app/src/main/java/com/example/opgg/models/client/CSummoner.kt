package com.example.opgg.models.client

import com.example.opgg.models.server.Summoner
import com.example.opgg.views.adapters.IMainListItem

class CSummoner(data: Summoner?) : IMainListItem {
    val name: String = data?.name ?: ""
    val imageUrl: String = data?.profileImageUrl ?: ""
    val level: String = data?.level?.toString() ?: ""
    val leagues: List<CLeague> = data?.leagues?.map { league -> CLeague(league) } ?: emptyList()
}