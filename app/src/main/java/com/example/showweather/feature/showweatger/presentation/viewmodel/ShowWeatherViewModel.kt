package com.example.showweather.feature.showweatger.presentation.viewmodel

import android.util.Log
import com.example.showweather.R
import com.example.showweather.feature.base.presentation.viewmodel.BaseViewModel
import com.example.showweather.feature.showweatger.domain.WeatherInteractor
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
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
            Log.e("TAG", "getWeather:  old Data")
            interactor.getWeather(context.resources.getString(R.string.api_key), true)
        }
    }

    fun savePositionSpinner(position: Int) {
        markChoiceItem(position)
        launchForm {
//            _listWeatherStateFlow.value?.listItems?.let {
//            it.find {item -> item.isChecked }
//            interactor.savePositionSpinner(it.find {item -> item.isChecked }) }
        }
    }

    fun getWeather() {
        launchFormInit(_listWeatherStateFlow) {
            Log.e("TAG", "getWeather:  new Data")
            interactor.getWeather(context.resources.getString(R.string.api_key), false)
        }
    }

    private fun markChoiceItem(position: Int) {
        var counter = 0
        _listWeatherStateFlow.value?.listItems?.map {
            it.isChecked = counter == position
            counter++
        }
    }
}