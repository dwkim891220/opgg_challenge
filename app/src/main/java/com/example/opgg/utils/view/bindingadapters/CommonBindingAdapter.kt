package com.example.opgg.utils.view.bindingadapters

import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.opgg.R
import com.example.opgg.utils.view.GlideApp

@BindingAdapter("show")
fun setLayoutShow(v: View, show: Boolean){
    v.visibility = if(show) View.VISIBLE else View.GONE
}

@BindingAdapter("glideUrl", "circleCrop", requireAll = false)
fun glideUrl(iv: ImageView, imageUrl: String?, circleCrop: Boolean = false) {
    if(imageUrl == null) return

    val glide = GlideApp.with(iv.context).load(Uri.parse(imageUrl))

    if(circleCrop) {
        glide.circleCrop().into(iv)
    }else {
        glide.fitCenter().into(iv)
    }
}

@BindingAdapter("displayStats")
fun displayStats(tv: TextView, stats: String?){
    if(stats == null) return

    val spBuilder = SpannableStringBuilder(stats).apply {
        setSpan(
            ForegroundColorSpan(ContextCompat.getColor(tv.context, R.color.darkish_pink)),
            stats.indexOfFirst { it in "/" }+1,
            stats.indexOfLast { it in "/" },
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    tv.text = spBuilder
}

@BindingAdapter("displayKDA")
fun displayKDA(tv: TextView, kda: String?){
    if(kda == null) return

    val spBuilder = SpannableStringBuilder(kda).apply {
        setSpan(
            ForegroundColorSpan(ContextCompat.getColor(tv.context, R.color.darkish_pink)),
            kda.indexOf("("),
            kda.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    tv.text = spBuilder
}