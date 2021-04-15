package com.arifrgilang.domain.paus.model


/**
 * Created by arifrgilang on 4/15/2021
 */
data class PausTokenDomainModel (
        val accessToken: String,
        val refreshToken: String,
        val expiresInSeconds: Int,
        val tokenType: String,
        val scopes: List<String>,
)