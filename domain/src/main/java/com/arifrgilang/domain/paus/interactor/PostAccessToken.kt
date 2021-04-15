package com.arifrgilang.domain.paus.interactor

import com.arifrgilang.domain.paus.PausRepository
import com.arifrgilang.domain.paus.model.PausTokenDomainModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by arifrgilang on 4/14/2021
 */
interface PostAccessToken {
    suspend fun execute(
            grantType: String,
            redirectUri: String,
            code: String,
            clientId: String,
            clientSecret: String
    ): Flow<PausTokenDomainModel>
}

class PostAccessTokenImpl(
        private val pausRepository: PausRepository
) : PostAccessToken {
    override suspend fun execute(
            grantType: String,
            redirectUri: String,
            code: String,
            clientId: String,
            clientSecret: String
    ): Flow<PausTokenDomainModel> =
            pausRepository.getAccessToken(
                    grantType,
                    redirectUri,
                    code,
                    clientId,
                    clientSecret
            )
}