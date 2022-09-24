package com.example.showweather.feature.showweatger.data.dto.mapper

import android.util.Log
import com.example.showweather.feature.showweatger.data.db.WeatherDao
import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.domain.model.CityItemModel
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity

fun WeatherResponse.Cord.map(name: String, id: String) = PointModelEntity(
    id = id,
    name = name,
    lon = lon,
    lan = lat
)

fun WeatherResponse.MainItem.map(id: String) = TempItemModelEntity(
    id = id,
    temp = temp
)

//fun WeatherResponse.map() =
//    ShowWeatherModel(
//        listItems = listOf(
//            CityItemModel(
//                cityPoint = PointModelEntity(
//                    name =,
//                    id =,
//                    lon =,
//                    lan =),
//                main = TempItemModelEntity(
//                    id =,
//                    temp =),
//                isChecked = false
//            )
//        )
//    )

fun getShowWeatherModel(
    points: List<PointModelEntity>,
    temp: List<TempItemModelEntity>
): ShowWeatherModel {
    val listResult = mutableListOf<CityItemModel>()
    points.forEach { point ->
        temp.find { point.id == it.id }
        listResult.add(
            CityItemModel(
                cityPoint = point,
                main = temp.firstOrNull() { point.id == it.id },
                isChecked = false
            )
        )
    }
    return ShowWeatherModel(listItems = listResult)
}

