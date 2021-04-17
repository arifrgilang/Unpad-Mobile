package com.arifrgilang.presentation.dashboard.mapper

import com.arifrgilang.domain.paus.model.StudentDomainModel
import com.arifrgilang.presentation.dashboard.model.StudentDataUiModel
import com.arifrgilang.presentation.dashboard.model.StudentUiModel


/**
 * Created by arifrgilang on 4/17/2021
 */
interface StudentDomainToUiModelMapper {
    fun toUiModel(studentDomainModel: StudentDomainModel): StudentUiModel
}

class StudentDomainToUiModelMapperImpl : StudentDomainToUiModelMapper {
    override fun toUiModel(studentDomainModel: StudentDomainModel): StudentUiModel =
        StudentUiModel(
            StudentDataUiModel(
                studentDomainModel.data!!.pausName,
                studentDomainModel.data!!.imageUrl,
                studentDomainModel.data!!.name,
                studentDomainModel.data!!.gender,
                studentDomainModel.data!!.entryYear,
                studentDomainModel.data!!.phoneNumber,
                studentDomainModel.data!!.email,
                studentDomainModel.data!!.npm,
                studentDomainModel.data!!.faculty
            ),
            studentDomainModel.error,
            studentDomainModel.errorMessage
        )
}