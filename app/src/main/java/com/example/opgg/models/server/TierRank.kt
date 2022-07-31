package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TierRank (
    @SerializedName("name") val name: String? = null,
    @SerializedName("tier") val tier: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("lp") val lp: Int? = null,
) : Parcelable