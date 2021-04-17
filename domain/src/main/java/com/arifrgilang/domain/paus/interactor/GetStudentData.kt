package com.arifrgilang.domain.paus.interactor

import com.arifrgilang.domain.paus.PausRepository
import com.arifrgilang.domain.paus.model.StudentDomainModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by arifrgilang on 4/17/2021
 */
interface GetStudentData {
    suspend fun execute(accessToken: String): Flow<StudentDomainModel>
}

class GetStudentDataImpl(
    private val pausRepository: PausRepository
) : GetStudentData {
    override suspend fun execute(accessToken: String): Flow<StudentDomainModel> =
        pausRepository.getStudentData(accessToken)
}