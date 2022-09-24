package com.example.showweather.feature.showweatger.domain.model

import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity


class CityItemModel(

    val cityPoint: PointModelEntity?,
    val main: TempItemModelEntity?,
    val isChecked: Boolean
)