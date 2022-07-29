package com.example.opgg.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position (
    @SerializedName("games") val games: Int? = null,
    @SerializedName("wins") val wins: Int? = null,
    @SerializedName("position") val position: String? = null,
) : Parcelable