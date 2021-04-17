package com.arifrgilang.presentation.main.model


/**
 * Created by arifrgilang on 4/17/2021
 */
data class PausTokenUiModel(
    val accessToken: String,
    val refreshToken: String,
    val expiresInSeconds: Int
)