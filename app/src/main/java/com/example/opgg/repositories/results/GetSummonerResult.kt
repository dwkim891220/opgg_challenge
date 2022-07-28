package com.example.opgg.repositories.results

import android.os.Parcelable
import com.example.opgg.models.Summoner
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetSummonerResult (
    @SerializedName("summoner") val summoner: Summoner? = null
) : Parcelable