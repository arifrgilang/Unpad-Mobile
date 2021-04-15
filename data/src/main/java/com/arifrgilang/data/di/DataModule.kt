package com.arifrgilang.data.di

import com.arifrgilang.data.paus.repository.PausRepositoryImpl
import com.arifrgilang.data.paus.repository.mapper.PausTokenRepositoryToDomainModelMapper
import com.arifrgilang.data.paus.repository.mapper.PausTokenRepositoryToDomainModelMapperImpl
import com.arifrgilang.data.paus.response.PausRemoteSource
import com.arifrgilang.data.paus.response.PausRemoteSourceImpl
import com.arifrgilang.data.paus.response.mapper.PausTokenResponseToRepositoryModelMapper
import com.arifrgilang.data.paus.response.mapper.PausTokenResponseToRepositoryModelMapperImpl
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
    single {
        get<Retrofit>().create(PausApi::class.java)
    }

    single<PausTokenResponseToRepositoryModelMapper> {
        PausTokenResponseToRepositoryModelMapperImpl()
    }

    single<PausRemoteSource> {
        PausRemoteSourceImpl(get(), get())
    }

    single<PausTokenRepositoryToDomainModelMapper> {
        PausTokenRepositoryToDomainModelMapperImpl()
    }

    single<PausRepository> {
        PausRepositoryImpl(get(), get())
    }
}