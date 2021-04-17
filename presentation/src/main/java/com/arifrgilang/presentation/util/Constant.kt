package com.arifrgilang.presentation.util

import android.net.Uri
import com.arifrgilang.presentation.BuildConfig


/**
 * Created by arifrgilang on 4/16/2021
 */
object Constant {
    fun pausOAuthUri(state: String): Uri = Uri.parse(BuildConfig.BASE_URL_PAUS + "/oauth")
        .buildUpon()
        .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
        .appendQueryParameter("redirect_uri", BuildConfig.REDIRECT_URI)
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("scope", BuildConfig.PAUS_SCOPE)
        .appendQueryParameter("state", state)
        .build()

    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"

}