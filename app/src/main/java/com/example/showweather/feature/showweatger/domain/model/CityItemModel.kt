package com.example.showweather.feature.showweatger.domain.model

import android.os.Parcelable
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityItemModel(

    val cityPoint: PointModelEntity?,
    val cityTemp: TempItemModelEntity?,
    val isChecked: Boolean
): Parcelable