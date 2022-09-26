package com.example.showweather.feature.searchcity.presentation.viewmodel

import com.example.showweather.feature.base.presentation.viewmodel.BaseViewModel
import com.example.showweather.util.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(

    @IoDispatcher
    ioDispatcher: CoroutineDispatcher,
) : BaseViewModel(ioDispatcher)