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
    abstract val isLoggedIn: LiveData<Event<Boolean>>
    abstract val pausToken: LiveData<PausTokenUiModel>
    abstract val isLoading: LiveData<Boolean>
    abstract val isError: LiveData<Event<Unit>>

    abstract fun getAccessToken(code: String)
    abstract fun startSession(accessToken: String)
    abstract fun checkUserToken()
}

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModelImpl(
    private val getAccessToken: PostAccessToken,
    private val pausTokenDomainToUiModelMapper: PausTokenDomainToUiModelMapper,
    private val userManager: UserManager
) : MainViewModel() {
    private val isLoggedInChannel = ConflatedBroadcastChannel<Event<Boolean>>()
    override val isLoggedIn: LiveData<Event<Boolean>> = isLoggedInChannel.asFlow().asLiveData()

    private val _pausToken = MediatorLiveData<PausTokenUiModel>()
    override val pausToken: LiveData<PausTokenUiModel>
        get() = _pausToken

    private val _isLoading = MediatorLiveData<Boolean>()
    override val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MediatorLiveData<Event<Unit>>()
    override val isError: LiveData<Event<Unit>>
        get() = _isError

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception.toString())
        _isLoading.value = false
    }

    override fun getAccessToken(code: String) {
        viewModelScope.launch(errorHandler) {
            _isLoading.value = true
            getAccessToken.execute(
                BuildConfig.PAUS_GRANT_TYPE,
                BuildConfig.REDIRECT_URI,
                code,
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET
            ).catch { throwable ->
                Timber.e(throwable.toString())
                _isError.postValue(eventOf(Unit))
                _isLoading.value = false
            }.collect { pausTokenDomainModel ->
                val pausTokenUiModel =
                    pausTokenDomainToUiModelMapper.toUiModel(pausTokenDomainModel)
                Timber.d(pausTokenUiModel.accessToken)
                _pausToken.postValue(pausTokenUiModel)
                _isLoading.value = false
            }
        }
    }

    override fun startSession(accessToken: String) {
        userManager.startUserSession(accessToken)
        isLoggedInChannel.offer(eventOf(true))
    }

    override fun checkUserToken() {
        if(userManager.isSessionActive()){
            isLoggedInChannel.offer(eventOf(true))
        }
    }
}