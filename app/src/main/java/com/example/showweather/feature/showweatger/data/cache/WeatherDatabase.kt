package com.example.showweather.feature.showweatger.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity

@Database(
    entities = [PointModelEntity::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeather(): WeatherDao
}