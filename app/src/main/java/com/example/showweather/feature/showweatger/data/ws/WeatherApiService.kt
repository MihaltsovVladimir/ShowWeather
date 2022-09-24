package com.example.showweather.feature.showweatger.data.ws

import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getListWeatherItems(
        @Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String
    ): WeatherResponse
}
