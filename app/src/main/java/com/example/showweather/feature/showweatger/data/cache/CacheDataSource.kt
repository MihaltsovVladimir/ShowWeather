package com.example.showweather.feature.showweatger.data.cache

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

interface CacheDataSource : FetchWeather {

    suspend fun insertPointAll(points: List<PointModelEntity>)

    suspend fun getPointModelAll(): List<PointModelEntity>

    override suspend fun getWeather(apiKey: String): ShowWeatherModel

    suspend fun insertData(item: PointModelEntity)

    suspend fun savePositionSpinner(checkedItem: PointModelEntity)

    class Base @Inject constructor(private val helper: DatabaseHelper) : CacheDataSource {

        override suspend fun insertPointAll(points: List<PointModelEntity>) =
            helper.insertCityAll(points)

        override suspend fun getPointModelAll() = helper.getPointModelAll()

        override suspend fun getWeather(apiKey: String) = helper.getPointModelAll().map()

        override suspend fun insertData(item: PointModelEntity) {
            helper.insertData(item)
        }

        override suspend fun savePositionSpinner(checkedItem: PointModelEntity) {
            helper.savePositionSpinner(checkedItem)
        }
    }
}

interface FetchWeather {
    suspend fun getWeather(apiKey: String): ShowWeatherModel
}