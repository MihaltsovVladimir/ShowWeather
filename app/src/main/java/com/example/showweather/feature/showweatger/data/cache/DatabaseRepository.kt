package com.example.showweather.feature.showweatger.data.cache

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

interface DatabaseRepository : FetchWeather {

    suspend fun insertPointAll(points: List<PointModelEntity>)

    suspend fun getPointModelAll(): List<PointModelEntity>

    override suspend fun getWeather(): ShowWeatherModel

    suspend fun savePositionSpinner(checkedItem: PointModelEntity)

    class Base @Inject constructor(private val helper: DatabaseHelper) : DatabaseRepository {

        override suspend fun insertPointAll(points: List<PointModelEntity>) =
            helper.insertCityAll(points)

        override suspend fun getPointModelAll() = helper.getPointModelAll()

        override suspend fun getWeather() = helper.getPointModelAll().map()

        override suspend fun savePositionSpinner(checkedItem: PointModelEntity) =
            helper.savePositionSpinner(checkedItem)
    }
}

interface FetchWeather {
    suspend fun getWeather(): ShowWeatherModel
}