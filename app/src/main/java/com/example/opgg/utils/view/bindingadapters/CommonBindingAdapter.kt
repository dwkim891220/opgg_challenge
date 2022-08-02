package com.example.opgg.utils.view.bindingadapters

import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.RoundedCorner
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import com.example.opgg.R
import com.example.opgg.models.client.OpBadge
import com.example.opgg.utils.view.GlideApp
import com.example.opgg.utils.view.show

@BindingAdapter("show")
fun setLayoutShow(v: View, show: Boolean){
    v.show(show)
}

@BindingAdapter("glideUrl", "circleCrop", "roundedCorners", requireAll = false)
fun glideUrl(iv: ImageView, imageUrl: String?, circleCrop: Boolean = false, roundedCorners: Int = 0) {
    if(imageUrl == null) return

    val glide = GlideApp.with(iv.context).load(Uri.parse(imageUrl))

    if(circleCrop) {
        glide.circleCrop().into(iv)
    }else {
        var options = RequestOptions().transform(FitCenter())

        if(roundedCorners > 0){
            options = options.transform(RoundedCorners(roundedCorners))
        }

        glide.fitCenter()
            .apply(options)
            .into(iv)
    }
}

@BindingAdapter("displayKDA")
fun displayKDA(tv: TextView, stats: String?){
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

@BindingAdapter("highlightContributionKillRate")
fun highlightContributionKillRate(tv: TextView, kda: String?){
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

@BindingAdapter("opBadge")
fun opBadge(tv: TextView, opBadge: OpBadge?){
    if(opBadge == null) {
        tv.show(false)
    }

    tv.text = opBadge?.displayString

    when(opBadge){
        OpBadge.Mvp -> tv.setBackgroundResource(R.drawable.r_badge_mvp)
        OpBadge.Ace -> tv.setBackgroundResource(R.drawable.r_badge_ace)
        else -> tv.show(false)
    }
}