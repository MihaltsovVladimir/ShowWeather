package com.example.showweather.feature.showweatger.data.repository

import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(

    private val helper: DatabaseHelperImpl
) {

    suspend fun getShowWeatherModel() = helper.getWeatherModel()

    suspend fun insertPointAll(points: List<PointModelEntity>) = helper.insertCityAll(points)

    suspend fun getPointModelAll() = helper.getPointModelAll()

}