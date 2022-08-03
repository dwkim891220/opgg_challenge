package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Summary (
    @SerializedName("wins") val wins: Int? = null,
    @SerializedName("losses") val losses: Int? = null,
    @SerializedName("kills") val kills: Int? = null,
    @SerializedName("deaths") val deaths: Int? = null,
    @SerializedName("assists") val assists: Int? = null,
) : Parcelable