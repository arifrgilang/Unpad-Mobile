package com.arifrgilang.presentation.main

import androidx.lifecycle.*
import com.arifrgilang.domain.paus.interactor.PostAccessToken
import com.arifrgilang.presentation.BuildConfig
import com.arifrgilang.presentation.main.mapper.PausTokenDomainToUiModelMapper
import com.arifrgilang.presentation.main.model.PausTokenUiModel
import com.arifrgilang.presentation.util.Event
import com.arifrgilang.presentation.util.UserManager
import com.arifrgilang.presentation.util.eventOf
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by arifrgilang on 4/17/2021
 */
abstract class MainViewModel : ViewModel() {

}

class MainViewModelImpl() : MainViewModel() {

}