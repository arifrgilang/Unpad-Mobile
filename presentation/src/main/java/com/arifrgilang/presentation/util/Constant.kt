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

    const val TITLE_REGULER = "Reguler Live Unpad"
    const val URL_REGULER = "https://reguler.live.unpad.ac.id/"

    const val TITLE_STUDENTS = "Students Unpad"
    const val URL_STUDENTS = "https://students.unpad.ac.id/pacis/"

    const val TITLE_PACAR = "Pacar Unpad"
    const val URL_PACAR = "https://www.instagram.com/pacarunpad/"
    const val URL_USER_PACAR_ = "https://www.instagram.com/_u/pacarunpad/"

    const val TITLE_UNPAD = "Website Unpad"
    const val URL_UNPAD = "https://www.unpad.ac.id/"

    const val TITLE_ODONG = "Tracking Angkutan Kampus"
    const val URL_ODONG = "https://siat.unpad.ac.id/sarpras/angkutankampus/"

    const val TITLE_JALATISTA = "Lokasi Jalatista"
    const val URL_JALATISTA = "https://siat.unpad.ac.id/sarpras/jalatista/"
}