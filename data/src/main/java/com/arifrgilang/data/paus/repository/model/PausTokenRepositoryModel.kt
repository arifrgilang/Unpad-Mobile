package com.arifrgilang.data.paus.repository.model

import com.google.gson.annotations.SerializedName


/**
 * Created by arifrgilang on 4/15/2021
 */
data class PausTokenRepositoryModel (
    val accessToken: String,
    val refreshToken: String,
    val expiresInSeconds: Int,
    val tokenType: String,
    val scopes: List<String>,
)