package com.example.showweather.feature.showweatger.domain

import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

interface WeatherInteractor {

    suspend fun addCityRoom()

    suspend fun getWeather(key: String, isFromDatabase: Boolean): ShowWeatherModel

    suspend fun savePositionSpinner(checkedItem: PointModelEntity)

    class Base @Inject constructor(

        private val repository: WeatherRepository.Base,
    ) : WeatherInteractor {
        override suspend fun addCityRoom() = repository.initRoom()

        override suspend fun getWeather(key: String, isFromDatabase: Boolean) =
            repository.getWeather(key, isFromDatabase)

        override suspend fun savePositionSpinner(checkedItem: PointModelEntity) =
            repository.savePositionSpinner(checkedItem)
    }
}