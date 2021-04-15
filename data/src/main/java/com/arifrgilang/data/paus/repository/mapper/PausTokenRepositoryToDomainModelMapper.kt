package com.arifrgilang.data.paus.repository.mapper

import com.arifrgilang.data.paus.repository.model.PausTokenRepositoryModel
import com.arifrgilang.domain.paus.model.PausTokenDomainModel


/**
 * Created by arifrgilang on 4/15/2021
 */
interface PausTokenRepositoryToDomainModelMapper {
    fun toDomainModel(pausTokenRepositoryModel: PausTokenRepositoryModel) : PausTokenDomainModel
}

class PausTokenRepositoryToDomainModelMapperImpl : PausTokenRepositoryToDomainModelMapper {
    override fun toDomainModel(pausTokenRepositoryModel: PausTokenRepositoryModel): PausTokenDomainModel =
            PausTokenDomainModel(
                    pausTokenRepositoryModel.accessToken,
                    pausTokenRepositoryModel.refreshToken,
                    pausTokenRepositoryModel.expiresInSeconds,
                    pausTokenRepositoryModel.tokenType,
                    pausTokenRepositoryModel.scopes
            )
}