package com.example.showweather.feature.showweatger.data.cloud

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.cache.FetchWeather
import com.example.showweather.feature.showweatger.data.cloud.ws.WeatherApiService
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import javax.inject.Inject

interface CloudDataSource : FetchWeather {

    class Base @Inject constructor(

        private val apiService: WeatherApiService,
        private val helper: DatabaseHelper.Base,
    ) : CloudDataSource {

        override suspend fun getWeather(apiKey: String): ShowWeatherModel {
            helper.getPointModelAll().forEach {
                val response =
                    apiService.getWeather(it.lat, it.lon, apiKey).map(it.name, it.isChecked)
                helper.insertData(response)
            }
            return helper.getPointModelAll().map()
        }
    }
}