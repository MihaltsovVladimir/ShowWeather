package com.example.showweather.feature.showweatger.data.cloud

import android.content.Context
import com.example.showweather.R
import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.cache.FetchWeather
import com.example.showweather.feature.showweatger.data.cloud.ws.WeatherApiService
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import javax.inject.Inject

interface CloudRepository : FetchWeather {

    class Base @Inject constructor(

        private val context: Context,
        private val apiService: WeatherApiService,
        private val helper: DatabaseHelper.Base,
    ) : CloudRepository {

        override suspend fun getWeather(): ShowWeatherModel {
            helper.getPointModelAll().forEach {
                val response =
                    apiService.getListWeatherItems(it.lat, it.lon, context.resources.getString(R.string.api_key)).map(it.name, it.isChecked)
                helper.insertData(response)
            }
            return helper.getPointModelAll().map()
        }
    }
}