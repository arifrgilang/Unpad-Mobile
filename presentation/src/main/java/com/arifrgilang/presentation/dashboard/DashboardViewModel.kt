package com.arifrgilang.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifrgilang.domain.paus.interactor.GetStudentData
import com.arifrgilang.presentation.dashboard.mapper.StudentDomainToUiModelMapper
import com.arifrgilang.presentation.dashboard.model.StudentUiModel
import com.arifrgilang.presentation.util.Event
import com.arifrgilang.presentation.util.UserManager
import com.arifrgilang.presentation.util.eventOf
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by arifrgilang on 4/17/2021
 */
abstract class DashboardViewModel : ViewModel() {
    abstract val studentData: LiveData<StudentUiModel>
    abstract val isLoading: LiveData<Boolean>
    abstract val isError: LiveData<Event<Unit>>

    abstract fun getStudentData()
}

@FlowPreview
@ExperimentalCoroutinesApi
class DashboardViewModelImpl(
    private val getStudentData: GetStudentData,
    private val studentDomainToUiModelMapper: StudentDomainToUiModelMapper,
    private val userManager: UserManager
) : DashboardViewModel() {
    private val _studentData = MediatorLiveData<StudentUiModel>()
    override val studentData: LiveData<StudentUiModel>
        get() = _studentData

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

    override fun getStudentData() {
        viewModelScope.launch(errorHandler) {
            _isLoading.value = true
            getStudentData.execute(
                "Bearer " + userManager.getUserAccessToken()
            ).catch { throwable ->
                Timber.e(throwable.toString())
                _isError.postValue(eventOf(Unit))
                _isLoading.value = false
            }.collect { studentDomainModel ->
                val studentUiModel =
                    studentDomainToUiModelMapper.toUiModel(studentDomainModel)
                _studentData.postValue(studentUiModel)
                _isLoading.value = false
            }
        }
    }
}