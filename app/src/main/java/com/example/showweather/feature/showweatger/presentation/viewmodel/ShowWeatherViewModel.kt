package com.example.showweather.feature.showweatger.presentation.viewmodel

import android.util.Log
import com.example.showweather.R
import com.example.showweather.feature.showweatger.data.db.WeatherDao
import com.example.showweather.feature.showweatger.data.repository.DatabaseRepository
import com.example.showweather.feature.showweatger.data.repository.NetworkRepository
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
    private val dataRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository,
) : BaseViewModel(ioDispatcher) {

    private val _listWeatherStateFlow = MutableStateFlow<ShowWeatherModel?>(null)
    val listWeatherStateFlow: StateFlow<ShowWeatherModel?>
        get() = _listWeatherStateFlow

    fun addCityToRoom(listCity: List<PointModelEntity>) {
        launchForm {
            if (dataRepository.getPointModelAll().isEmpty())
                 dataRepository.insertPointAll(listCity)
        }
    }

    fun getWeather() {
        launchForm {
            val listCity = dataRepository.getPointModelAll()
            Log.e("TAG", "ViewModel size: ${listCity.size}")
            _listWeatherStateFlow.emit(dataRepository.getShowWeatherModel())
            networkRepository.getWeather(listCity, context.resources.getString(R.string.api_key))
            _listWeatherStateFlow.emit(dataRepository.getShowWeatherModel())
        }
    }
}