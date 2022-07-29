package com.example.opgg.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Summoner (
    @SerializedName("name") val name: String? = null,
    @SerializedName("level") val level: Int? = null,
    @SerializedName("profileImageUrl") val profileImageUrl: String? = null,
    @SerializedName("leagues") val leagues: List<League>? = null,
) : Parcelable