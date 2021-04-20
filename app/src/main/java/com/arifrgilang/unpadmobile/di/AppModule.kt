package com.arifrgilang.unpadmobile.di

import com.arifrgilang.data.service.paus.PausApi
import com.arifrgilang.data.service.paus.PausAuthInterceptor
import com.arifrgilang.data.service.paus.PausRetrofitFactory
import com.arifrgilang.presentation.BuildConfig
import okhttp3.Credentials
import org.koin.dsl.module


/**
 * Created by arifrgilang on 4/14/2021
 */
val appModule = module {
    single {
        PausRetrofitFactory.provideHttpLoggingInterceptor(BuildConfig.DEBUG)
    }

    single {
        PausRetrofitFactory.provideOkHttpClientBuilder(get())
    }

    single {
        PausRetrofitFactory.provideRetrofitBuilder(BuildConfig.BASE_URL_PAUS)
    }

//    single {
//        PausAuthInterceptor(
//            Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
//        )
//    }

    single {
        PausRetrofitFactory.providePausService(
            PausApi::class.java, get(), get()
        )
    }
}