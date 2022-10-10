package com.example.showweather.feature.showweatger.domain.model

import android.os.Parcelable
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowWeatherModel(

    val listItems: List<PointModelEntity>,
) : Parcelable