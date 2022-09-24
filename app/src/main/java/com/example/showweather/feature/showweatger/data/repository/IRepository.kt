package com.example.showweather.feature.showweatger.data.repository

import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity

interface IRepository {

    suspend fun getWeather(listPointModel: List<PointModelEntity>, key:String): ShowWeatherModel

}
