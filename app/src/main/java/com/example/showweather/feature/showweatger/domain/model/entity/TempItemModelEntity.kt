package com.example.showweather.feature.showweatger.domain.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "temp_item_table")
data class TempItemModelEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "temp") val temp: String
) : Parcelable