package com.example.showweather.feature.showweatger.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

open class BaseViewModel(

    private var ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    @SuppressLint("StaticFieldLeak") //Это ApplicationContext
    @Inject
    protected lateinit var context: Context

     fun <R> launchForm(block: suspend CoroutineScope.() -> R) =
        viewModelScope.launch(ioDispatcher) { supervisorScope(block) }
}