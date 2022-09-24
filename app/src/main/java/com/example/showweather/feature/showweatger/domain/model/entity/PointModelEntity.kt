package com.example.showweather.feature.showweatger.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_position_table")
data class PointModelEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "lan") val lan: Double
)