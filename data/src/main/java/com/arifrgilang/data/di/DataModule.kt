package com.arifrgilang.data.di

import com.arifrgilang.data.paus.repository.PausRepositoryImpl
import com.arifrgilang.data.paus.repository.mapper.PausTokenRepositoryToDomainModelMapper
import com.arifrgilang.data.paus.repository.mapper.PausTokenRepositoryToDomainModelMapperImpl
import com.arifrgilang.data.paus.repository.mapper.StudentRepositoryToDomainModelMapper
import com.arifrgilang.data.paus.repository.mapper.StudentRepositoryToDomainModelMapperImpl
import com.arifrgilang.data.paus.response.PausRemoteSource
import com.arifrgilang.data.paus.response.PausRemoteSourceImpl
import com.arifrgilang.data.paus.response.mapper.PausTokenResponseToRepositoryModelMapper
import com.arifrgilang.data.paus.response.mapper.PausTokenResponseToRepositoryModelMapperImpl
import com.arifrgilang.data.paus.response.mapper.StudentResponseToRepositoryModelMapper
import com.arifrgilang.data.paus.response.mapper.StudentResponseToRepositoryModelMapperImpl
import com.arifrgilang.data.service.paus.PausApi
import com.arifrgilang.domain.paus.PausRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by arifrgilang on 4/14/2021
 */

@FlowPreview
@ExperimentalCoroutinesApi
val dataModule = module {

    // PausToken Mapper
    single<PausTokenResponseToRepositoryModelMapper> {
        PausTokenResponseToRepositoryModelMapperImpl()
    }
    single<PausTokenRepositoryToDomainModelMapper> {
        PausTokenRepositoryToDomainModelMapperImpl()
    }

    // Student Mapper
    single<StudentResponseToRepositoryModelMapper> {
        StudentResponseToRepositoryModelMapperImpl()
    }
    single<StudentRepositoryToDomainModelMapper> {
        StudentRepositoryToDomainModelMapperImpl()
    }

    // Source Impl
    single<PausRemoteSource> {
        PausRemoteSourceImpl(get(), get(), get())
    }
    single<PausRepository> {
        PausRepositoryImpl(get(), get(), get())
    }
}