package com.example.showweather.feature.base.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showweather.feature.showweatger.domain.model.ErrorModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

open class BaseViewModel(

    private var ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    @Inject
    protected lateinit var context: Context

    fun <R> launchForm(block: suspend CoroutineScope.() -> R) =
        viewModelScope.launch(ioDispatcher) { supervisorScope(block) }

    fun <T : Any> launchFormInit(
        flow: MutableStateFlow<T?>,
        customErrorFlow: MutableSharedFlow<ErrorModel?>? = null,
        block: suspend CoroutineScope.() -> T
    ) = launchSupervisorScope {
        processFormInitJobWithFlow(customErrorFlow, block).collectLatest { flow.emit(it) }
    }

    private fun <T : Any> processFormInitJobWithFlow(
        customErrorFlow: MutableSharedFlow<ErrorModel?>? = null,
        block: suspend CoroutineScope.() -> T
    ) = processJobWithFlow(customErrorFlow = customErrorFlow, block = block)


    private fun <R> launchSupervisorScope(block: suspend CoroutineScope.() -> R) =
        scope.launch { supervisorScope(block) }

    private val scope = CoroutineScope(Dispatchers.IO)

    private fun <T : Any> processJobWithFlow(
        formInit: Boolean = true,
        customErrorFlow: MutableSharedFlow<ErrorModel?>? = null,
        block: suspend CoroutineScope.() -> T
    ) = flow {
        emit(block.invoke(scope))
    }.catch {
        // processRequestError(it, formInit, customErrorFlow)
    }
}
