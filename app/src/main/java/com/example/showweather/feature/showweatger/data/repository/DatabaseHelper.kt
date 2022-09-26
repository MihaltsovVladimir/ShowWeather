package com.example.showweather.feature.showweatger.data.repository

import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity

interface DatabaseHelper {

    suspend fun getWeatherModel(): ShowWeatherModel

    suspend fun insertCityAll(points: List<PointModelEntity>)

    suspend fun getPointModelAll(): List<PointModelEntity>

    suspend fun savePositionSpinner(id: String)

    suspend fun insertData(response: WeatherResponse, point: PointModelEntity)

    suspend fun deleteItemWithId(id: String)

    suspend fun clearDataBase()
}