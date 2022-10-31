package com.example.showweather.feature.showweatger.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM city_position_table")
    fun getPointModelAll(): List<PointModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(point: PointModelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityAll(points: List<PointModelEntity>)
}