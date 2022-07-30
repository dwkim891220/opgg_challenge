package com.example.opgg.models.client

import com.example.opgg.models.server.Game
import java.util.*

class CGame(data: Game?) {
    val isWin: Boolean = data?.isWin ?: false
    val championImageUrl: String = data?.champion?.imageUrl ?: ""
    val opBadge: String = data?.stats?.general?.opScoreBadge ?: ""
    val contributionKillRate: String = data?.stats?.general?.contributionForKillRate ?: ""
    val gameType: String = data?.gameType ?: ""
    val multiKillString: String = data?.stats?.general?.largestMultiKillString ?: ""
    val createTime: Long? = data?.createDate

    val spellImageUrls: List<String>
    val runeImageUrls: List<String>
    val itemImageUrls: List<String>
    val gameLength: String
    val displayCreateTime: String

    init {
        spellImageUrls =
            if(data?.spells != null && data.spells.isNotEmpty()) {
                data.spells
                    .filter { spell -> spell.imageUrl != null && spell.imageUrl.isNotEmpty() }
                    .map { spell -> spell.imageUrl!! }
            } else {
                emptyList()
            }

        runeImageUrls =
            if(data?.peak != null && data.peak.isNotEmpty()){
                data.peak
                    .filter { peakItem -> peakItem.isNotEmpty() }
                    .map { peakItem -> peakItem }
            }else{
                emptyList()
            }

        itemImageUrls =
            if(data?.items != null && data.items.isNotEmpty()){
                data.items
                    .filter { item -> item.imageUrl != null && item.imageUrl.isNotEmpty() }
                    .map { item -> item.imageUrl!! }
            }else{
                emptyList()
            }

        gameLength = if(data?.gameLength != null && data.gameLength.length > 4){
            "${data.gameLength.substring(0, 1)}:${data.gameLength.substring(2, 3)}"
        }else {
            ""
        }

        displayCreateTime = createTime?.run { parsingDisplayCreateDate(this) } ?: ""
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
            diffTimeSeconds > monthSeconds -> "${diffTimeSeconds / monthSeconds}달 전"
            diffTimeSeconds > daySeconds -> "${diffTimeSeconds / daySeconds}일 전"
            diffTimeSeconds > hourSeconds -> "${diffTimeSeconds / hourSeconds}시간 전"
            diffTimeSeconds > minSeconds -> "${diffTimeSeconds / minSeconds}분 전"
            else -> "방금전"
        }
    }
}