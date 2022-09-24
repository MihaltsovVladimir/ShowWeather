package com.example.showweather.feature.showweatger.data.db

import androidx.room.*
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM city_position_table")
    fun getPointModelAll(): List<PointModelEntity>

    @Query("SELECT * FROM temp_item_table")
    fun getTempItemModelAll(): List<TempItemModelEntity>

    @Query("DELETE FROM city_position_table WHERE id in (:ids)")
    fun deletePointModelEntity(ids: List<String>)

    @Query("DELETE FROM temp_item_table WHERE id in (:ids)")
    fun deleteTempItemModelEntity(ids: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg point: PointModelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg point: TempItemModelEntity)

    @Delete
    fun deletePointModel(point: PointModelEntity)

    @Delete
    fun deleteTempItemModel(point: TempItemModelEntity)

    @Query("DELETE FROM city_position_table")
    fun deletePointModelAll()

    @Query("DELETE FROM temp_item_table")
    fun deleteTempItemModelAll()
}