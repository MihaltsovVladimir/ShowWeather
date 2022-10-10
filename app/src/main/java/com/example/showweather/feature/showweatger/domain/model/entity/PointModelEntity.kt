package com.example.showweather.feature.showweatger.domain.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "city_position_table")
data class PointModelEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lon") val lon: String,
    @ColumnInfo(name = "lan") val lat: String,
    @ColumnInfo(name = "temperature") var temperature: String?,
    @ColumnInfo(name = "isChecked") var isChecked: Boolean,
) : Parcelable