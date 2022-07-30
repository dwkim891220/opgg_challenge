package com.example.opgg.models.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item (
    @SerializedName("imageUrl") val imageUrl: String? = null,
) : Parcelable