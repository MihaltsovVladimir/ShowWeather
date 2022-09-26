package com.example.showweather.feature.showweatger.data.repository

import com.example.showweather.feature.showweatger.data.ws.WeatherApiService
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

class NetworkRepository @Inject constructor(

    private val apiService: WeatherApiService,
    private val helper: DatabaseHelperImpl
) {
    suspend fun getWeather(listPointModel: List<PointModelEntity>, key: String): ShowWeatherModel {
        listPointModel.forEach {
            helper.deleteItemWithId(it.id)
            val response = apiService.getListWeatherItems(it.lat, it.lon, key)
            helper.insertData(response, it)
        }
        return helper.getWeatherModel()
    }
}