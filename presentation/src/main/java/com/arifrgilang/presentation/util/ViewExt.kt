package com.arifrgilang.presentation.util

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast


/**
 * Created by arifrgilang on 4/14/2021
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Button.enable() {
    isEnabled = true
}

fun Button.disable() {
    isEnabled = false
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}