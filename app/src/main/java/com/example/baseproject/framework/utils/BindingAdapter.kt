package com.example.baseproject.framework.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import com.example.baseproject.business.entities.Address

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .diskCacheStrategy(ALL)
        .into(view)
}


@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["address"], requireAll = true)
fun setAddress(view: TextView, address: Address?) {
    view.text="${address?.street} ${address?.suite} ${address?.city} ${address?.zipcode}"
}