package com.arifrgilang.domain.di

import com.arifrgilang.domain.paus.PausRepository
import com.arifrgilang.domain.paus.interactor.PostAccessToken
import com.arifrgilang.domain.paus.interactor.PostAccessTokenImpl
import org.koin.dsl.module


/**
 * Created by arifrgilang on 4/15/2021
 */
val domainModule = module {
    single<PostAccessToken> {
        PostAccessTokenImpl(get())
    }
}