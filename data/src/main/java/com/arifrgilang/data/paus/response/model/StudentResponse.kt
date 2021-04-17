package com.arifrgilang.data.paus.response.model

import com.google.gson.annotations.SerializedName


/**
 * Created by arifrgilang on 4/17/2021
 */
data class StudentResponse(
    @SerializedName("paus_name")
    val pausName: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("attributes")
    val attributes: AttributesResponse?,
    @SerializedName("groups")
    val groups: WrappedStudentGroupResponse?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val errorMessage: String?
)

data class AttributesResponse(
    @SerializedName("nama")
    val name: AttributeResponse,
    @SerializedName("jenis_kelamin")
    val gender: AttributeResponse,
    @SerializedName("tanggal_lahir")
    val birthDate: AttributeResponse,
    @SerializedName("tahun_masuk")
    val entryYear: AttributeResponse,
    @SerializedName("paus_id")
    val pausId: AttributeResponse,
    @SerializedName("paus_status")
    val pausStatus: AttributeResponse,
    @SerializedName("paus_role")
    val pausRole: AttributeResponse,
    @SerializedName("nama_depan")
    val firstName: AttributeResponse,
    @SerializedName("nama_belakang")
    val lastName: AttributeResponse,
    @SerializedName("nomor_handphone")
    val phoneNumber: AttributeResponse,
    @SerializedName("email_alternatif")
    val email: AttributeResponse,
    @SerializedName("nomor_kartu_identitas")
    val ktpNumber: AttributeResponse,
    @SerializedName("npm")
    val npm: AttributeNPMResponse,
    @SerializedName("fakultas")
    val faculty: AttributeResponse,
    @SerializedName("pintas_status")
    val pintasStatus: AttributeResponse
)

data class AttributeResponse(
    @SerializedName("attribute_code")
    val attributeCode: String,
    @SerializedName("attribute_name")
    val attributeName: String,
    @SerializedName("attribute_value")
    val attributeValue: String,
)

data class AttributeNPMResponse(
    @SerializedName("attribute_code")
    val attributeCode: String,
    @SerializedName("attribute_name")
    val attributeName: String,
    @SerializedName("attribute_value")
    val attributeValue: List<String>,
)

data class GroupResponse(
    @SerializedName("group_code")
    val groupCode: String,
    @SerializedName("group_name")
    val groupName: String,
    @SerializedName("group_ldap_role")
    val groupRole: Int
)

data class WrappedStudentGroupResponse(
    @SerializedName("mahasiswa")
    val student: GroupResponse
)