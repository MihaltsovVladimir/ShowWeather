package com.example.showweather.feature.showweatger.data.repository

import android.util.Log
import com.example.showweather.feature.showweatger.data.db.WeatherDao
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import com.example.showweather.feature.showweatger.domain.model.CityItemModel
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.domain.model.entity.TempItemModelEntity
import javax.inject.Inject

class DatabaseHelperImpl @Inject constructor(

    private val weatherDao: WeatherDao
) : DatabaseHelper {

    override suspend fun getWeatherModel(): ShowWeatherModel {
        val points = weatherDao.getPointModelAll()
        val temp = weatherDao.getTempItemModelAll()
        return getShowWeatherModel(points, temp)
    }

    override suspend fun insertCityAll(points: List<PointModelEntity>) {
        weatherDao.insertCityAll(points)
    }

    override suspend fun getPointModelAll(): List<PointModelEntity> = weatherDao.getPointModelAll()

    override suspend fun insertData(response: WeatherResponse, point: PointModelEntity) {
        weatherDao.insert(response.coord.map(point.name, point.id))
        weatherDao.insert(response.main.map(point.id))
    }

    override suspend fun deleteItemWithId(id: String) {
        weatherDao.deleteTempItemModel(id)
        weatherDao.deletePointModel(id)
    }

    override suspend fun clearDataBase() {
        weatherDao.deletePointModelAll()
        weatherDao.deleteTempItemModelAll()
    }


    private fun getShowWeatherModel(
        points: List<PointModelEntity>,
        temp: List<TempItemModelEntity>
    ): ShowWeatherModel {
        val listResult = mutableListOf<CityItemModel>()
        points.forEach { point ->
            temp.find { point.id == it.id }
            listResult.add(
                CityItemModel(
                    cityPoint = point,
                    cityTemp = temp.firstOrNull() { point.id == it.id },
                    isChecked = false
                )
            )
        }
        listResult.forEach {
            Log.e("TAG", "listResult cityTemp: ${it.cityTemp?.temp}")
        }
        return ShowWeatherModel(listItems = listResult)
    }
}