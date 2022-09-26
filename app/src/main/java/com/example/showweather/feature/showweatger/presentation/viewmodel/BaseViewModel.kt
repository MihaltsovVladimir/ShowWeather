package com.example.showweather.feature.showweatger.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showweather.feature.showweatger.domain.model.ErrorModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

open class BaseViewModel(

    private var ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    @Inject
    protected lateinit var context: Context

    fun <R> launchForm(block: suspend CoroutineScope.() -> R) =
        viewModelScope.launch(ioDispatcher) { supervisorScope(block) }


    fun <T : Any> BaseViewModel.launchFormInit(
        flow: MutableStateFlow<T?>,
        customErrorFlow: MutableSharedFlow<ErrorModel?>? = null,
        block: suspend CoroutineScope.() -> T
    ) = launchSupervisorScope {
        processFormInitJobWithFlow(customErrorFlow, block).collectLatest { flow.emit(it) }
    }

    fun <T : Any> processFormInitJobWithFlow(
        customErrorFlow: MutableSharedFlow<ErrorModel?>? = null,
        block: suspend CoroutineScope.() -> T
    ) = processJobWithFlow(customErrorFlow = customErrorFlow, block = block)


    fun <R> BaseViewModel.launchSupervisorScope(block: suspend CoroutineScope.() -> R) =
        scope.launch { supervisorScope(block) }

    val scope = CoroutineScope(Dispatchers.IO)

    private fun <T : Any> processJobWithFlow(
        formInit: Boolean = true,
        customErrorFlow: MutableSharedFlow<ErrorModel?>? = null,
        block: suspend CoroutineScope.() -> T
    ) = flow {
        emit(block.invoke(scope))
    }.onCompletion {
        //decrementLoadingCounter()
    }.onStart {
        Log.e("TAG", "flow started ")
        // incrementLoadingCounter()
    }.retryWhen { cause, attempt ->
        return@retryWhen cause is IOException && attempt < 3 // Logic for retry
    }.catch {
        // processRequestError(it, formInit, customErrorFlow)
    }
}
