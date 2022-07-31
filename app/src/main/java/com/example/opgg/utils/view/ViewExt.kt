package com.example.opgg.utils.view

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.CircleCrop

@BindingAdapter("glideUrl", "circleCrop", requireAll = false)
fun glideUrl(iv: ImageView, imageUrl: String?, circleCrop: Boolean = false) {
    GlideApp.with(iv.context)
        .load(Uri.parse(imageUrl))
        .let { request ->
            if(circleCrop) request.transform(CircleCrop()) else request
        }.fitCenter()
        .into(iv)
}