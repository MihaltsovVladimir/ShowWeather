package com.example.showweather.feature.showweatger.data

import com.example.showweather.feature.showweatger.data.cache.WeatherDao
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

interface DatabaseHelper {

    suspend fun insertCityAll(points: List<PointModelEntity>)

    suspend fun getPointModelAll(): List<PointModelEntity>

    suspend fun insertData(response: PointModelEntity)

    suspend fun savePositionSpinner(checkedItem: PointModelEntity)

    class Base @Inject constructor(private val weatherDao: WeatherDao) : DatabaseHelper {

        override suspend fun insertCityAll(points: List<PointModelEntity>) {
            weatherDao.insertCityAll(points)
        }

        override suspend fun getPointModelAll(): List<PointModelEntity> =
            weatherDao.getPointModelAll()

        override suspend fun insertData(response: PointModelEntity) {
            weatherDao.insert(response)
        }

        override suspend fun savePositionSpinner(checkedItem: PointModelEntity) {
            TODO("Not yet implemented")
        }
    }
}