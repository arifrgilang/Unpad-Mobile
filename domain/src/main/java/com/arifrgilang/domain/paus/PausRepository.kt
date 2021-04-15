package com.arifrgilang.domain.paus

import com.arifrgilang.domain.paus.model.PausTokenDomainModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by arifrgilang on 4/15/2021
 */
interface PausRepository {
    suspend fun getAccessToken(
            grantType: String,
            redirectUri: String,
            code: String,
            clientId: String,
            clientSecret: String
    ) : Flow<PausTokenDomainModel>
}