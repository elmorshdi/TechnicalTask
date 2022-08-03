package com.elmorshdi.technicaltask.view.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setPrice")
fun setPrice(textView: AppCompatTextView, price: String?) {
    val text = StringBuilder().append(price).append(" ").append("LE").toString()
    textView.text = text
}
@BindingAdapter("loadImageUrl")
fun loadImage(imageView: ImageView, url: String?) {

    if (!url.isNullOrBlank()) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}