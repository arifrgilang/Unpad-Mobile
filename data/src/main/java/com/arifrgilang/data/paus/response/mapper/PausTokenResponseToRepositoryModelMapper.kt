package com.arifrgilang.data.paus.response.mapper

import com.arifrgilang.data.paus.repository.model.PausTokenRepositoryModel
import com.arifrgilang.data.paus.response.model.PausTokenResponse


/**
 * Created by arifrgilang on 4/15/2021
 */
interface PausTokenResponseToRepositoryModelMapper {
    fun toRepositoryModel(pausTokenResponse: PausTokenResponse) : PausTokenRepositoryModel
}

class PausTokenResponseToRepositoryModelMapperImpl : PausTokenResponseToRepositoryModelMapper {
    override fun toRepositoryModel(pausTokenResponse: PausTokenResponse): PausTokenRepositoryModel =
            PausTokenRepositoryModel(
                    pausTokenResponse.accessToken,
                    pausTokenResponse.refreshToken,
                    pausTokenResponse.expiresInSeconds,
                    pausTokenResponse.tokenType,
                    pausTokenResponse.scopes
            )
}