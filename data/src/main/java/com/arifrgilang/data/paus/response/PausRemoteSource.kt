package com.arifrgilang.data.paus.response

import com.arifrgilang.data.paus.response.mapper.PausTokenResponseToRepositoryModelMapper
import com.arifrgilang.data.paus.repository.model.PausTokenRepositoryModel
import com.arifrgilang.data.paus.repository.model.StudentRepositoryModel
import com.arifrgilang.data.paus.response.mapper.StudentResponseToRepositoryModelMapper
import com.arifrgilang.data.service.paus.PausApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow


/**
 * Created by arifrgilang on 4/14/2021
 */
interface PausRemoteSource {
    suspend fun getAccessToken(
        grantType: String,
        redirectUri: String,
        code: String,
        clientId: String,
        clientSecret: String
    ): Flow<PausTokenRepositoryModel>

    suspend fun getStudentData(
        accessToken: String
    ): Flow<StudentRepositoryModel>
}

@FlowPreview
@ExperimentalCoroutinesApi
class PausRemoteSourceImpl(
    private val apiService: PausApi,
    private val pausTokenRepositoryModelMapper: PausTokenResponseToRepositoryModelMapper,
    private val studentRepositoryModelMapper: StudentResponseToRepositoryModelMapper
) : PausRemoteSource {
    private val pausTokenChannel = ConflatedBroadcastChannel<PausTokenRepositoryModel>()
    private val studentChannel = ConflatedBroadcastChannel<StudentRepositoryModel>()

    override suspend fun getAccessToken(
        grantType: String,
        redirectUri: String,
        code: String,
        clientId: String,
        clientSecret: String
    ): Flow<PausTokenRepositoryModel> {
        val pausTokenResponse = apiService.getAccessToken(
            grantType,
            redirectUri,
            code,
            clientId,
            clientSecret
        )
        val pausTokenRepositoryModel =
            pausTokenRepositoryModelMapper.toRepositoryModel(pausTokenResponse)
        pausTokenChannel.offer(pausTokenRepositoryModel)
        return pausTokenChannel.asFlow()
    }

    override suspend fun getStudentData(accessToken: String): Flow<StudentRepositoryModel> {
        val studentResponse = apiService.getStudentData(accessToken)
        val studentRepositoryModel =
            studentRepositoryModelMapper.toRepositoryModel(studentResponse)
        studentChannel.offer(studentRepositoryModel)
        return studentChannel.asFlow()
    }
}