package com.example.showweather.feature.showweatger.domain

import com.example.showweather.feature.showweatger.data.cache.CacheDataSource
import com.example.showweather.feature.showweatger.data.cloud.CloudDataSource
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import javax.inject.Inject

interface WeatherRepository {

    suspend fun getWeather(apiKey: String, isFromDatabase: Boolean): ShowWeatherModel

    class Base @Inject constructor(

        private val dataRepository: CacheDataSource.Base,
        private val cloudRepository: CloudDataSource.Base,
    ) : WeatherRepository {

        suspend fun initRoom() {
            if (dataRepository.getPointModelAll().isEmpty()) {
                dataRepository.insertPointAll(createInitLocality())
            }
        }

        override suspend fun getWeather(apiKey: String, isFromDatabase: Boolean): ShowWeatherModel {
            val dataSource = if (isFromDatabase) dataRepository else cloudRepository
            return dataSource.getWeather(apiKey)
        }

        suspend fun savePositionSpinner(checkedItem: PointModelEntity) =
            dataRepository.savePositionSpinner(checkedItem)

        private fun createInitLocality(): List<PointModelEntity> = listOf(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false),
            PointModelEntity("Minsk", "53.902284", "27.561831", null, false),
            PointModelEntity("Tokio", "49.088732", "33.413770", null, false)
        )
    }
}