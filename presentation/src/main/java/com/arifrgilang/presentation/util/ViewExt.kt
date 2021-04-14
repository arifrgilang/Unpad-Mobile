package com.arifrgilang.presentation.util

import android.view.View
import android.widget.Button


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