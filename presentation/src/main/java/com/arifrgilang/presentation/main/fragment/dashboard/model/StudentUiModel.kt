package com.arifrgilang.presentation.main.fragment.dashboard.model

/**
 * Created by arifrgilang on 4/17/2021
 */
data class StudentUiModel(
    val data: StudentDataUiModel?,
    val error: String?,
    val errorMessage: String?
)

data class StudentDataUiModel(
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