package com.example.showweather.feature.showweatger.data.cloud.ws

import com.example.showweather.feature.showweatger.data.dto.response.WeatherResponse

class MockWeatherApiService : WeatherApiService {

    override suspend fun getWeather(lat: String, lon: String, appid: String) = WeatherResponse(
        coord = WeatherResponse.Cord(53.9023, 27.5618),
        weather = listOf(WeatherResponse.WeatherItem(804, "Clouds", "overcast clouds", "04n")),
        base = "stations",
        main = WeatherResponse.MainItem(293.13, 292.25, 293.13, 293.13, 1016, 41, 1016, 894),
        visibility = 10000,
        wind = WeatherResponse.WindItem(1.47, 259, 1.56),
        clouds = WeatherResponse.CloudsItem(96),
        dt = 1667328750,
        sys = WeatherResponse.SysItem(1, 7497, "IR", 1667270084, 1667310066),
        timezone = 12600,
        id = 133595,
        name = "Gerāsh",
        cod = 200
    )
}