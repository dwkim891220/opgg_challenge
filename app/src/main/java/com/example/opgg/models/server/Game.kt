package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    @SerializedName("champion") val champion: Champion? = null,
    @SerializedName("spells") val spells: List<Spell>? = null,
    @SerializedName("peak") val peak: List<String>? = null,
    @SerializedName("items") val items: List<Item>? = null,
    @SerializedName("createDate") val createDate: Long? = null,
    @SerializedName("gameType") val gameType: String? = null,
    @SerializedName("gameLength") val gameLength: Int? = null,
    @SerializedName("isWin") val isWin: Boolean? = null,
    @SerializedName("stats") val stats: Stat? = null,
) : Parcelable