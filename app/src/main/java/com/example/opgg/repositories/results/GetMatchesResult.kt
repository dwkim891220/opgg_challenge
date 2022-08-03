package com.example.opgg.repositories.results

import android.os.Parcelable
import com.example.opgg.models.server.Champion
import com.example.opgg.models.server.Game
import com.example.opgg.models.server.Position
import com.example.opgg.models.server.Summary
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class GetMatchesResult (
    @SerializedName("games") val games: List<Game>? = null,
    @SerializedName("champions") val champions: List<Champion>? = null,
    @SerializedName("positions") val positions: List<Position>? = null,
    @SerializedName("summary") val summary: Summary? = null
) : Parcelable