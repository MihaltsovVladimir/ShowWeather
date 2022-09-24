package com.example.showweather.feature.showweatger.data.repository

import com.example.showweather.feature.showweatger.data.db.WeatherDao
import com.example.showweather.feature.showweatger.data.dto.mapper.getShowWeatherModel
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.data.ws.WeatherApiService
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(

    private val weatherDao: WeatherDao
) : IRepository {

    override suspend fun getWeather(
        listPointModel: List<PointModelEntity>,
        key: String
    ): ShowWeatherModel {
        val points = weatherDao.getPointModelAll()
        val temp = weatherDao.getTempItemModelAll()
        return getShowWeatherModel(points, temp)
    }
}