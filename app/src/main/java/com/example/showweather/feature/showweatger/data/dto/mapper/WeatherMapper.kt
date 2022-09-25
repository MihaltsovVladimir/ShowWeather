package com.example.showweather.feature.showweatger.data.dto.mapper

import android.util.Log
import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.domain.model.CityItemModel
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity

fun WeatherResponse.Cord.map(name: String, id: String) = PointModelEntity(
    id = id,
    name = name,
    lon = lon.toString(),
    lat = lat.toString()
)

fun WeatherResponse.MainItem.map(id: String) = TempItemModelEntity(
    id = id,
    temp = temp.toString()
)

fun WeatherResponse.map(pointModel: PointModelEntity) =
    ShowWeatherModel(
        listItems = listOf(
            CityItemModel(
                cityPoint = PointModelEntity(
                    name = pointModel.name,
                    id = pointModel.id,
                    lon = coord.lon.toString(),
                    lat = coord.lat.toString()
                ),
                cityTemp = TempItemModelEntity(
                    id = pointModel.id,
                    temp = main.temp.toString()
                ),
                isChecked = false
            )
        )
    )

