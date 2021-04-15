package com.arifrgilang.data.paus.response.model

import com.google.gson.annotations.SerializedName


/**
 * Created by arifrgilang on 4/14/2021
 */
data class PausTokenResponse (
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresInSeconds: Int,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("scope")
    val scopes: List<String>,
)