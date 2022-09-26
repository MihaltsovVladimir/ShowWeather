package com.example.showweather.feature.showweatger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity

@Database(
    entities = [
        PointModelEntity::class,
        TempItemModelEntity::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeather(): WeatherDao
}