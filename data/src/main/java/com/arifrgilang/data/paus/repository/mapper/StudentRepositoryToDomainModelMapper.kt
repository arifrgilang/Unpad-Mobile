package com.arifrgilang.data.paus.repository.mapper

import com.arifrgilang.data.paus.repository.model.StudentRepositoryModel
import com.arifrgilang.domain.paus.model.StudentDataDomainModel
import com.arifrgilang.domain.paus.model.StudentDomainModel


/**
 * Created by arifrgilang on 4/17/2021
 */
interface StudentRepositoryToDomainModelMapper {
    fun toDomainModel(studentRepositoryModel: StudentRepositoryModel): StudentDomainModel
}

class StudentRepositoryToDomainModelMapperImpl : StudentRepositoryToDomainModelMapper {
    override fun toDomainModel(studentRepositoryModel: StudentRepositoryModel): StudentDomainModel =
        StudentDomainModel(
            StudentDataDomainModel(
                studentRepositoryModel.data!!.pausName,
                studentRepositoryModel.data.imageUrl,
                studentRepositoryModel.data.name,
                studentRepositoryModel.data.gender,
                studentRepositoryModel.data.entryYear,
                studentRepositoryModel.data.phoneNumber,
                studentRepositoryModel.data.email,
                studentRepositoryModel.data.npm,
                studentRepositoryModel.data.faculty
            ),
            studentRepositoryModel.error,
            studentRepositoryModel.errorMessage
        )
}