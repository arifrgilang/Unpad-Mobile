package com.arifrgilang.presentation.main.fragment.dashboard.mapper

import com.arifrgilang.domain.paus.model.StudentDomainModel
import com.arifrgilang.presentation.main.fragment.dashboard.model.StudentDataUiModel
import com.arifrgilang.presentation.main.fragment.dashboard.model.StudentUiModel


/**
 * Created by arifrgilang on 4/17/2021
 */
interface StudentDomainToUiModelMapper {
    fun toUiModel(studentDomainModel: StudentDomainModel): StudentUiModel
}

class StudentDomainToUiModelMapperImpl : StudentDomainToUiModelMapper {
    override fun toUiModel(studentDomainModel: StudentDomainModel): StudentUiModel {
        var gender = ""
        studentDomainModel.data?.gender?.let{
            when(it){
                "male" -> gender = "Laki-laki"
                "female" -> gender = "Perempuan"
            }
        }
        return StudentUiModel(
            StudentDataUiModel(
                studentDomainModel.data!!.pausName,
                studentDomainModel.data!!.imageUrl,
                studentDomainModel.data!!.name,
                gender,
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
}