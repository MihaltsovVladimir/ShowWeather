package com.example.showweather.feature.showweatger.data

import com.example.showweather.feature.showweatger.data.cache.WeatherDao
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

interface DatabaseHelper {

    suspend fun insertCityAll(points: List<PointModelEntity>)

    suspend fun getPointModelAll(): List<PointModelEntity>

    suspend fun insertData(item: PointModelEntity)

    suspend fun savePositionSpinner(checkedItem: PointModelEntity)

    class Base @Inject constructor(private val weatherDao: WeatherDao) : DatabaseHelper {

        private val mutex = Mutex()

        override suspend fun insertCityAll(points: List<PointModelEntity>) = mutex.withLock {
            weatherDao.insertCityAll(points)
        }

        override suspend fun getPointModelAll(): List<PointModelEntity> = mutex.withLock {
            weatherDao.getPointModelAll()
        }

        override suspend fun insertData(item: PointModelEntity) = mutex.withLock {
            weatherDao.insert(item)
        }

        override suspend fun savePositionSpinner(checkedItem: PointModelEntity) = mutex.withLock {
            val listItems = weatherDao.getPointModelAll().toMutableList()
            listItems.map { it.isChecked = it.name == checkedItem.name }
            weatherDao.insertCityAll(listItems)
        }
    }
}