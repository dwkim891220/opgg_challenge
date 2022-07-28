package com.example.opgg.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class League (
    @SerializedName("wins") val level: Int? = null,
    @SerializedName("losses") val profileImageUrl: String? = null,
    @SerializedName("tierRank") val profileBorderImageUrl: String? = null,
) : Parcelable