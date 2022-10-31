package com.example.showweather

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.cache.WeatherDao
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class DatabaseHelperTest(
    private val weatherDao: WeatherDao
) : DatabaseHelper {

    private val mutex = Mutex()

    override suspend fun insertCityAll(points: List<PointModelEntity>) = mutex.withLock {
        weatherDao.insertCityAll(points)
    }

    override suspend fun getPointModelAll(): List<PointModelEntity>  = mutex.withLock{
        weatherDao.getPointModelAll()
    }

    override suspend fun insertData(response: PointModelEntity) = mutex.withLock{
        weatherDao.insert(response)
    }

    override suspend fun savePositionSpinner(checkedItem: PointModelEntity) = mutex.withLock{
        val listItems = getPointModelAll()
        listItems.map { it.isChecked = it.name == checkedItem.name }
        weatherDao.insertCityAll(listItems)
    }
}