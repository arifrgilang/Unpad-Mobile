package com.arifrgilang.presentation.di

import com.arifrgilang.presentation.main.MainViewModel
import com.arifrgilang.presentation.main.MainViewModelImpl
import com.arifrgilang.presentation.main.mapper.PausTokenDomainToUiModelMapper
import com.arifrgilang.presentation.main.mapper.PausTokenDomainToUiModelMapperImpl
import com.arifrgilang.presentation.util.UserManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by arifrgilang on 4/17/2021
 */
val presentationModule = module {
    single {
        UserManager()
    }

    single<PausTokenDomainToUiModelMapper> {
        PausTokenDomainToUiModelMapperImpl()
    }

    viewModel<MainViewModel> {
        MainViewModelImpl(get(), get(), get())
    }
}