package com.arifrgilang.presentation.main.fragment.dashboard

import androidx.lifecycle.*
import com.arifrgilang.domain.paus.interactor.GetStudentData
import com.arifrgilang.domain.paus.interactor.PostAccessToken
import com.arifrgilang.presentation.BuildConfig
import com.arifrgilang.presentation.main.fragment.dashboard.mapper.StudentDomainToUiModelMapper
import com.arifrgilang.presentation.main.fragment.dashboard.model.StudentUiModel
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
abstract class DashboardViewModel : ViewModel() {
    abstract val isLoggedIn: LiveData<Event<Boolean>>
    abstract val pausToken: LiveData<PausTokenUiModel>
    abstract val studentData: LiveData<StudentUiModel>
    abstract val isLoading: LiveData<Boolean>
    abstract val isError: LiveData<Event<Unit>>
    abstract val isErrorToken: LiveData<Event<String>>

    abstract fun getAccessToken(code: String)
    abstract fun startSession(accessToken: String)
    abstract fun endUserSession()
    abstract fun checkUserToken()
    abstract fun getStudentData()
}

@FlowPreview
@ExperimentalCoroutinesApi
class DashboardViewModelImpl(
    private val getStudentData: GetStudentData,
    private val studentDomainToUiModelMapper: StudentDomainToUiModelMapper,
    private val getAccessToken: PostAccessToken,
    private val pausTokenDomainToUiModelMapper: PausTokenDomainToUiModelMapper,
    private val userManager: UserManager
) : DashboardViewModel() {
    private val isLoggedInChannel = ConflatedBroadcastChannel<Event<Boolean>>()
    override val isLoggedIn: LiveData<Event<Boolean>> = isLoggedInChannel.asFlow().asLiveData()

    private val _pausToken = MediatorLiveData<PausTokenUiModel>()
    override val pausToken: LiveData<PausTokenUiModel>
        get() = _pausToken

    private val _studentData = MediatorLiveData<StudentUiModel>()
    override val studentData: LiveData<StudentUiModel>
        get() = _studentData

    private val _isLoading = MediatorLiveData<Boolean>()
    override val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MediatorLiveData<Event<Unit>>()
    override val isError: LiveData<Event<Unit>>
        get() = _isError

    private val _isErrorToken = MediatorLiveData<Event<String>>()
    override val isErrorToken: LiveData<Event<String>>
        get() = _isErrorToken

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception.toString())
        _isLoading.value = false
    }

    private val errorHandlerStudent = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception.toString())
        _isLoading.value = false
        _isErrorToken.postValue(eventOf(exception.message!! + ". Please relogin!"))
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
                _pausToken.postValue(pausTokenUiModel)
                _isLoading.value = false
            }
        }
    }

    override fun startSession(accessToken: String) {
        userManager.startUserSession(accessToken)
        isLoggedInChannel.offer(eventOf(true))
    }

    override fun endUserSession() {
        userManager.endUserSession()
        isLoggedInChannel.offer(eventOf(false))
    }


    override fun checkUserToken() {
        userManager.isSessionActive()?.let{ isActive ->
            if(isActive) isLoggedInChannel.offer(eventOf(true))
        }
    }

    override fun getStudentData() {
        viewModelScope.launch(errorHandlerStudent) {
            _isLoading.value = true
            getStudentData.execute(
                "Bearer " + userManager.getUserAccessToken()
            ).catch { throwable ->
                Timber.e(throwable.toString())
                _isError.postValue(eventOf(Unit))
                _isErrorToken.postValue(eventOf(throwable.message!!))
                _isLoading.value = false
            }.collect { studentDomainModel ->
                val studentUiModel =
                    studentDomainToUiModelMapper.toUiModel(studentDomainModel)
                Timber.d(studentUiModel.toString())
                _studentData.postValue(studentUiModel)
                _isLoading.value = false
            }
        }
    }
}