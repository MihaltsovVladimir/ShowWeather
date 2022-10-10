package com.example.showweather.feature.showweatger.data.dto.mapper

import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity

fun WeatherResponse.map(name: String, isChecked: Boolean) = PointModelEntity(
    name = name,
    lon = coord.lon.toString(),
    lat = coord.lat.toString(),
    temperature = main.temp.toString(),
    isChecked = isChecked
)

fun List<PointModelEntity>.map(): ShowWeatherModel {
    return ShowWeatherModel(listItems = this)
}