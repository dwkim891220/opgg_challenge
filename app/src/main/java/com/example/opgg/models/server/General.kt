package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class General (
    @SerializedName("kill") val kills: Int? = null,
    @SerializedName("death") val deaths: Int? = null,
    @SerializedName("assist") val assists: Int? = null,
    @SerializedName("opScoreBadge") val opScoreBadge: String? = null,
    @SerializedName("contributionForKillRate") val contributionForKillRate: String? = null,
    @SerializedName("largestMultiKillString") val largestMultiKillString: String? = null,
) : Parcelable