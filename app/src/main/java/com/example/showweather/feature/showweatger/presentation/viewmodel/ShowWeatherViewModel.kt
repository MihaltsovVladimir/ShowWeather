package com.example.showweather.feature.showweatger.presentation.viewmodel

import com.example.showweather.R
import com.example.showweather.feature.showweatger.data.db.WeatherDao
import com.example.showweather.feature.showweatger.data.repository.DatabaseRepository
import com.example.showweather.feature.showweatger.data.repository.NetworkRepository
import com.example.showweather.feature.showweatger.domain.model.CityItemModel
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
    private val repository: DatabaseRepository,
    private val networkRepository: NetworkRepository,
    private val localSource: WeatherDao
) : BaseViewModel(ioDispatcher) {

    private val _listWeatherStateFlow = MutableStateFlow<ShowWeatherModel?>(null)
    val listWeatherStateFlow: StateFlow<ShowWeatherModel?>
        get() = _listWeatherStateFlow

    fun getWeather() {
        launchForm {
            _listWeatherStateFlow.emit(getShowWeatherModel())
            val listCity = localSource.getPointModelAll()
            val response = networkRepository.getWeather(listCity, context.resources.getString(R.string.api_key))
            _listWeatherStateFlow.emit(response)
        }
    }

    fun addCityToRoom(listCity: List<PointModelEntity>) {
        launchForm {
            listCity.forEach { localSource.insert(it) }
        }
    }

    private suspend fun getShowWeatherModel(): ShowWeatherModel {
        val listResult = mutableListOf<CityItemModel>()
        val points = localSource.getPointModelAll()
        val temp = localSource.getTempItemModelAll()
        points.forEach { point ->
            temp.find { point.id == it.id }
            listResult.add(
                CityItemModel(
                    cityPoint = point,
                    main = temp.firstOrNull() { point.id == it.id },
                    isChecked = false
                )
            )
        }
        return ShowWeatherModel(listItems = listResult)
    }
}