package com.arifrgilang.data.paus.repository.model


/**
 * Created by arifrgilang on 4/17/2021
 */
data class StudentRepositoryModel(
    val data: StudentDataRepositoryModel?,
    val error: String?,
    val errorMessage: String?
)

data class StudentDataRepositoryModel(
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