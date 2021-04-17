package com.arifrgilang.data.paus.response.mapper

import com.arifrgilang.data.paus.repository.model.StudentDataRepositoryModel
import com.arifrgilang.data.paus.repository.model.StudentRepositoryModel
import com.arifrgilang.data.paus.response.model.StudentResponse


/**
 * Created by arifrgilang on 4/17/2021
 */
interface StudentResponseToRepositoryModelMapper {
    fun toRepositoryModel(studentResponse: StudentResponse): StudentRepositoryModel
}

class StudentResponseToRepositoryModelMapperImpl : StudentResponseToRepositoryModelMapper {
    override fun toRepositoryModel(studentResponse: StudentResponse): StudentRepositoryModel =
        StudentRepositoryModel(
            StudentDataRepositoryModel(
                studentResponse.pausName!!,
                studentResponse.imageUrl!!,
                studentResponse.attributes!!.name.attributeValue,
                studentResponse.attributes.gender.attributeValue,
                studentResponse.attributes.entryYear.attributeValue,
                studentResponse.attributes.phoneNumber.attributeValue,
                studentResponse.attributes.email.attributeValue,
                studentResponse.attributes.npm.attributeValue[0],
                studentResponse.attributes.faculty.attributeValue
            ),
            studentResponse.error,
            studentResponse.errorMessage
        )
}