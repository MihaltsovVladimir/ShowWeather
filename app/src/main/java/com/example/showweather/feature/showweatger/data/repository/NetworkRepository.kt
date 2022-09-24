package com.example.showweather.feature.showweatger.data.repository

import android.util.Log
import com.example.showweather.feature.showweatger.data.db.WeatherDao
import com.example.showweather.feature.showweatger.data.dto.mapper.getShowWeatherModel
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.data.ws.WeatherApiService
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

class NetworkRepository @Inject constructor(

    private val apiService: WeatherApiService,
    private val weatherDao: WeatherDao
) : IRepository {
    override suspend fun getWeather(listPointModel: List<PointModelEntity>, key: String): ShowWeatherModel {
        listPointModel.forEach {
            val response = apiService.getListWeatherItems(it.lan.toString(), it.lon.toString(), key)
            handleResponse(response, it)
        }
        val points = weatherDao.getPointModelAll()
        val temp = weatherDao.getTempItemModelAll()
        return getShowWeatherModel(points, temp)
    }

    private fun handleResponse(response: WeatherResponse, point: PointModelEntity) {
        clearDataBase()
        weatherDao.insert(response.coord.map(point.name, point.id))
        weatherDao.insert(response.main.map(point.id))
    }


    private fun clearDataBase() {
        weatherDao.deletePointModelAll()
        weatherDao.deleteTempItemModelAll()
    }
}