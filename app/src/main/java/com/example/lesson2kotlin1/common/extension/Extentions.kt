package com.example.lesson2kotlin1.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) =
    Glide.with(this).load(url).into(this)
