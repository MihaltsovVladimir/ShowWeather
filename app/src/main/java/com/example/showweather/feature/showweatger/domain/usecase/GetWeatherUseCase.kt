package com.example.showweather.feature.showweatger.domain.usecase

import com.example.showweather.feature.showweatger.data.repository.DatabaseRepository
import com.example.showweather.feature.showweatger.data.repository.NetworkRepository
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject


class GetWeatherUseCase @Inject constructor(

    private val dataRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository,
) {

    suspend fun addCityToRoom(listCity: List<PointModelEntity>) {
        if (dataRepository.getPointModelAll().isEmpty())
            dataRepository.insertPointAll(listCity)
    }

    suspend fun savePositionSpinner(id: String) = dataRepository.savePositionSpinner(id)


    suspend fun getWeatherFromDatabase(): ShowWeatherModel = dataRepository.getShowWeatherModel()

    suspend fun getWeather(key: String): ShowWeatherModel {
        val listCity = dataRepository.getPointModelAll()
        networkRepository.getWeather(listCity, key).apply {
            return networkRepository.getWeather(listCity, key)
        }
    }
}
