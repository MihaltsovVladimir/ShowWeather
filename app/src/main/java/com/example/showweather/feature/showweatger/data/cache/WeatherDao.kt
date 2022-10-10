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

    @Query("DELETE FROM city_position_table WHERE name in (:names)")
    fun deletePointModelEntity(names: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg point: PointModelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityAll(points: List<PointModelEntity>)
}