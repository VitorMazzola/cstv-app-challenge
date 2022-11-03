package com.challenge.cstvapp.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.challenge.cstvapp.R

val requestOptions = RequestOptions()
    .transforms(CenterCrop(), RoundedCorners(16))

fun String.loadTeamsLogo(context: Context, view: ImageView) {
    Glide.with(context)
        .load(this)
        .placeholder(R.drawable.circle_shape_placeholder)
        .into(view)
}

fun String.loadPicturePlayer(context: Context, view: ImageView) {
    Glide.with(context)
        .load(this)
        .apply(requestOptions)
        .placeholder(R.drawable.rounded_shape_placeholder)
        .into(view)
}