package com.example.opgg.repositories.results

import android.os.Parcelable
import com.example.opgg.models.Champion
import com.example.opgg.models.Game
import com.example.opgg.models.Position
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class GetMatchesResult (
    @SerializedName("games") val games: List<Game>? = null,
    @SerializedName("champions") val champions: List<Champion>? = null,
    @SerializedName("positions") val positions: List<Position>? = null
) : Parcelable