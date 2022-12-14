package com.example.opgg.models.client

import com.example.opgg.models.server.Game
import com.example.opgg.views.adapters.IMainListItem
import java.util.*

class CGame(data: Game?) : IMainListItem {
    val isWin: Boolean = data?.isWin ?: false
    val contributionKillRate: String = data?.stats?.general?.contributionForKillRate ?: ""
    val gameType: String = data?.gameType ?: ""
    val multiKillString: String = data?.stats?.general?.largestMultiKillString ?: ""
    val createDate: Long? = data?.createDate
    val kills: Int = data?.stats?.general?.kills ?: 0
    val deaths: Int = data?.stats?.general?.deaths ?: 0
    val assists: Int = data?.stats?.general?.assists ?: 0
    val champion: CChampion = CChampion(data?.champion)
    val kda: String = "$kills / $deaths / $assists"

    var spellImageUrls: List<String>
    var runeImageUrls: List<String>
    var itemImageUrls: List<String>
    val gameLength: String
    val displayCreateTime: String
    val opBadge: OpBadge

    private val defaultSpellImageLength = 2
    private val defaultRuneImageLength = 2
    private val defaultItemsImageLength = 6

    init {
        spellImageUrls =
            if(data?.spells != null && data.spells.isNotEmpty()) {
                data.spells
                    .filter { spell -> spell.imageUrl != null && spell.imageUrl.isNotEmpty() }
                    .map { spell -> spell.imageUrl!! }
            } else {
                emptyList()
            }

        if(spellImageUrls.size < defaultSpellImageLength) {
            spellImageUrls = getEmptyStringList(spellImageUrls, defaultSpellImageLength)
        }

        runeImageUrls =
            if(data?.peak != null && data.peak.isNotEmpty()){
                data.peak
                    .filter { peakItem -> peakItem.isNotEmpty() }
                    .map { peakItem -> peakItem }
            }else{
                emptyList()
            }

        if(runeImageUrls.size < defaultRuneImageLength) {
            runeImageUrls = getEmptyStringList(runeImageUrls, defaultRuneImageLength)
        }

        itemImageUrls =
            if(data?.items != null && data.items.isNotEmpty()){
                data.items
                    .filter { item -> item.imageUrl != null && item.imageUrl.isNotEmpty() }
                    .map { item -> item.imageUrl!! }
            }else{
                emptyList()
            }

        if(itemImageUrls.size < defaultItemsImageLength) {
            itemImageUrls = getEmptyStringList(itemImageUrls, defaultItemsImageLength)
        }

        gameLength = if(data?.gameLength != null) {
            val minute = data.gameLength/60
            String.format(
                "%.2s:%.2s",
                minute.toString().padStart(2, '0'),
                (data.gameLength-(minute*60)).toString().padStart(2, '0')
            )
        }else{
            ""
        }

        displayCreateTime = createDate?.run { parsingDisplayCreateDate(this) } ?: ""

        opBadge =
            when(data?.stats?.general?.opScoreBadge){
                "ACE" -> OpBadge.Ace
                "MVP" -> OpBadge.Mvp
                else -> OpBadge.None
            }
    }

    private fun parsingDisplayCreateDate(time: Long): String{
        val currentDateTime = Date().time
        val createDateTime = Date(time * 1000).time

        if(createDateTime > currentDateTime) return ""

        val diffTimeSeconds = (currentDateTime - createDateTime) / 1000

        val minSeconds = 60
        val hourSeconds = minSeconds * 60
        val daySeconds = hourSeconds * 24
        val monthSeconds = daySeconds * 30

        return when{
            diffTimeSeconds > monthSeconds -> "${diffTimeSeconds / monthSeconds}??? ???"
            diffTimeSeconds > daySeconds -> "${diffTimeSeconds / daySeconds}??? ???"
            diffTimeSeconds > hourSeconds -> "${diffTimeSeconds / hourSeconds}?????? ???"
            diffTimeSeconds > minSeconds -> "${diffTimeSeconds / minSeconds}??? ???"
            else -> "?????????"
        }
    }

    private fun getEmptyStringList(existList:List<String>, length: Int): List<String>{
        val list = existList.toMutableList()

        for (index in (list.size)..length){
            list.add("")
        }

        return list.toList()
    }
}

enum class OpBadge(val displayString: String) {
    Ace("ACE"),
    Mvp("MVP"),
    None("")
}