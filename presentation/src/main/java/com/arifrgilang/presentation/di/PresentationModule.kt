package com.arifrgilang.presentation.di

import com.arifrgilang.presentation.main.fragment.dashboard.DashboardViewModel
import com.arifrgilang.presentation.main.fragment.dashboard.DashboardViewModelImpl
import com.arifrgilang.presentation.main.fragment.dashboard.mapper.StudentDomainToUiModelMapper
import com.arifrgilang.presentation.main.fragment.dashboard.mapper.StudentDomainToUiModelMapperImpl
import com.arifrgilang.presentation.main.MainViewModel
import com.arifrgilang.presentation.main.MainViewModelImpl
import com.arifrgilang.presentation.main.mapper.PausTokenDomainToUiModelMapper
import com.arifrgilang.presentation.main.mapper.PausTokenDomainToUiModelMapperImpl
import com.arifrgilang.presentation.util.UserManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by arifrgilang on 4/17/2021
 */
@ExperimentalCoroutinesApi
@FlowPreview
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

    single<StudentDomainToUiModelMapper> {
        StudentDomainToUiModelMapperImpl()
    }

    viewModel<DashboardViewModel> {
        DashboardViewModelImpl(get(), get(), get(), get(), get())
    }
}