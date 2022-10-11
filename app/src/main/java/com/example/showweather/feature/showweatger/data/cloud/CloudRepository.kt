package com.example.showweather.feature.showweatger.data.cloud

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.cache.FetchWeather
import com.example.showweather.feature.showweatger.data.cloud.ws.WeatherApiService
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import javax.inject.Inject

interface CloudRepository : FetchWeather {

    class Base @Inject constructor(

        private val apiService: WeatherApiService,
        private val helper: DatabaseHelper.Base,
    ) : CloudRepository {

        override suspend fun getWeather(key: String): ShowWeatherModel {
            helper.getPointModelAll().forEach {
                val response =
                    apiService.getListWeatherItems(it.lat, it.lon, key).map(it.name, it.isChecked)
                helper.insertData(response)
            }
            return helper.getPointModelAll().map()
        }
    }
}