package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Champion (
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("games") val games: Int? = null,
    @SerializedName("wins") val wins: Int? = null,
) : Parcelable