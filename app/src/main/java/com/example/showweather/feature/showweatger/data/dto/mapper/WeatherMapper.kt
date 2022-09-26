package com.example.showweather.feature.showweatger.data.dto.mapper

import android.content.Context
import com.example.showweather.R
import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity

fun WeatherResponse.Cord.map(name: String, id: String) = PointModelEntity(
    id = id,
    name = name,
    lon = lon.toString(),
    lat = lat.toString()
)

fun WeatherResponse.MainItem.map(id: String, context: Context) = TempItemModelEntity(
    id = id,
    temp = "$temp ${context.resources.getString(R.string.fahrenheit)}"
)

