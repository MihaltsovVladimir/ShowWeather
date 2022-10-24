package com.example.showweather.feature.showweatger.presentation.viewmodel

import com.example.showweather.feature.base.presentation.viewmodel.BaseViewModel
import com.example.showweather.feature.showweatger.domain.WeatherInteractor
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.util.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ShowWeatherViewModel @Inject constructor(

    @IoDispatcher
    ioDispatcher: CoroutineDispatcher,
    private val interactor: WeatherInteractor.Base,
) : BaseViewModel(ioDispatcher) {

    private val _listWeatherStateFlow = MutableStateFlow<ShowWeatherModel?>(null)
    val listWeatherStateFlow: StateFlow<ShowWeatherModel?>
        get() = _listWeatherStateFlow

    init {
        launchForm { interactor.addCityRoom() }
    }

    fun getDataFromDatabase() {
        launchFormInit(_listWeatherStateFlow) {
            interactor.getWeather(true)
        }
    }

    fun savePositionSpinner(position: PointModelEntity) {
        launchFormInit(_listWeatherStateFlow) {
            interactor.savePositionSpinner(position)
            interactor.getWeather(true)
        }
    }

    fun getWeather() {
        launchFormInit(_listWeatherStateFlow) {
            interactor.getWeather(false)
        }
    }
}