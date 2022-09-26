package com.example.showweather.feature.showweatger.presentation.viewmodel

import com.example.showweather.R
import com.example.showweather.feature.base.presentation.viewmodel.BaseViewModel
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.usecase.GetWeatherUseCase
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
    private val useCase: GetWeatherUseCase
) : BaseViewModel(ioDispatcher) {

    private val _listWeatherStateFlow = MutableStateFlow<ShowWeatherModel?>(null)
    val listWeatherStateFlow: StateFlow<ShowWeatherModel?>
        get() = _listWeatherStateFlow

    fun addCityToRoom(listCity: List<PointModelEntity>) {
        launchForm { useCase.addCityToRoom(listCity) }
    }

    private fun getWeatherFromDatabase() {
        launchFormInit(_listWeatherStateFlow) {
            useCase.getWeatherFromDatabase()
        }
    }

    fun savePositionSpinner(position: Int) {
        markChoiceItem(position)
        launchForm { _listWeatherStateFlow.value?.listItems?.let { useCase.savePositionSpinner(it) } }
    }

    fun getWeather() {
        getWeatherFromDatabase()
        launchFormInit(_listWeatherStateFlow) {
            useCase.getWeather(context.resources.getString(R.string.api_key))
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