package com.arifrgilang.data.service.paus

import com.arifrgilang.data.paus.response.model.PausTokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by arifrgilang on 4/14/2021
 */
interface PausApi {
    @FormUrlEncoded
    @POST("/oauth/access-token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ) : PausTokenResponse
}