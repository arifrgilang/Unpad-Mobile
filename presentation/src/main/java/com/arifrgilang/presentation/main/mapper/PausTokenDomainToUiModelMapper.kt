package com.arifrgilang.presentation.main.mapper

import com.arifrgilang.domain.paus.model.PausTokenDomainModel
import com.arifrgilang.presentation.main.model.PausTokenUiModel


/**
 * Created by arifrgilang on 4/17/2021
 */
interface PausTokenDomainToUiModelMapper {
    fun toUiModel(pausTokenDomainModel: PausTokenDomainModel): PausTokenUiModel
}

class PausTokenDomainToUiModelMapperImpl: PausTokenDomainToUiModelMapper {
    override fun toUiModel(pausTokenDomainModel: PausTokenDomainModel): PausTokenUiModel =
        PausTokenUiModel(
            pausTokenDomainModel.accessToken,
            pausTokenDomainModel.refreshToken,
            pausTokenDomainModel.expiresInSeconds
        )
}