package com.arifrgilang.domain.paus.model


/**
 * Created by arifrgilang on 4/17/2021
 */
data class StudentDomainModel(
    val data: StudentDataDomainModel?,
    val error: String?,
    val errorMessage: String?
)

data class StudentDataDomainModel(
    val pausName: String,
    val imageUrl: String,
    val name: String,
    val gender: String,
    val entryYear: String,
    val phoneNumber: String,
    val email: String,
    val npm: String,
    val faculty: String
)