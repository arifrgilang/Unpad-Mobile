package com.arifrgilang.presentation.util

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arifrgilang.presentation.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


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

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform()

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .error(ContextCompat.getDrawable(context, R.drawable.error_no_picture))
        .into(this)
}

fun ImageView.loadBadge(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun ImageView.loadPath(path: String){

    val myBitmap = BitmapFactory.decodeFile(path)
    val options = RequestOptions()
        .error(R.drawable.error_no_picture)
        .centerCrop()
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(myBitmap)
        .into(this)
}


fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}


@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?){
    view.loadImage(url,
        getProgressDrawable(view.context)
    )
}
