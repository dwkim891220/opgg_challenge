package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class League (
    @SerializedName("wins") val wins: Int? = null,
    @SerializedName("losses") val losses: Int? = null,
    @SerializedName("tierRank") val tierRank: TierRank? = null,
) : Parcelable