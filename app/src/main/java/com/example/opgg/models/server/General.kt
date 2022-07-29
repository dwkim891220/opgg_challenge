package com.example.opgg.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class General (
    @SerializedName("kills") val kills: Int? = null,
    @SerializedName("deaths") val deaths: Int? = null,
    @SerializedName("assists") val assists: Int? = null,
    @SerializedName("opScoreBadge") val opScoreBadge: String? = null,
    @SerializedName("contributionForKillRate") val contributionForKillRate: String? = null,
) : Parcelable