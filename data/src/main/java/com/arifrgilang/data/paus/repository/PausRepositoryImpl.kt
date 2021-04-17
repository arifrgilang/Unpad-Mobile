package com.arifrgilang.data.paus.repository

import com.arifrgilang.data.paus.repository.mapper.PausTokenRepositoryToDomainModelMapper
import com.arifrgilang.data.paus.repository.mapper.StudentRepositoryToDomainModelMapper
import com.arifrgilang.data.paus.response.PausRemoteSource
import com.arifrgilang.domain.paus.PausRepository
import com.arifrgilang.domain.paus.model.PausTokenDomainModel
import com.arifrgilang.domain.paus.model.StudentDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by arifrgilang on 4/15/2021
 */
class PausRepositoryImpl(
    private val pausRemoteSource: PausRemoteSource,
    private val pausTokenRepositoryToDomainModelMapper: PausTokenRepositoryToDomainModelMapper,
    private val studentRepositoryToDomainModelMapper: StudentRepositoryToDomainModelMapper
) : PausRepository {
    override suspend fun getAccessToken(
        grantType: String,
        redirectUri: String,
        code: String,
        clientId: String,
        clientSecret: String
    ): Flow<PausTokenDomainModel> =
        pausRemoteSource.getAccessToken(
            grantType,
            redirectUri,
            code,
            clientId,
            clientSecret
        ).map { pausTokenRepositoryModel ->
            pausTokenRepositoryToDomainModelMapper.toDomainModel(pausTokenRepositoryModel)
        }

    override suspend fun getStudentData(
        accessToken: String
    ): Flow<StudentDomainModel> =
        pausRemoteSource.getStudentData(accessToken)
            .map { studentRepositoryModel ->
                studentRepositoryToDomainModelMapper.toDomainModel(studentRepositoryModel)
            }
}