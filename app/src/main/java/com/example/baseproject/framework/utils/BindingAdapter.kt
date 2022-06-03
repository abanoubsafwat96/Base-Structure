package com.example.baseproject.framework.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.baseproject.business.entities.Address

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun ImageView.loadImage(imageUrl: String, placeholder: Drawable?) {
    this.load(imageUrl) {
        placeholder(placeholder)
        fallback(placeholder)
        error(placeholder)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["address"], requireAll = false)
fun setAddress(view: TextView, address: Address?) {
    address?.let {
        view.text = "${it.street}, ${it.suite}, ${it.city}, ${it.zipcode}"
    }
}