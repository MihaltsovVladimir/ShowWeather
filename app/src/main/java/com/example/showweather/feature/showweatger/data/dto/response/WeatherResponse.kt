package com.example.showweather.feature.showweatger.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
class WeatherResponse(

    val coord: Cord,
    val weather: List<WeatherItem>,
    val base: String,
    val main: MainItem,
    val visibility: Int,
    val wind: WindItem,
    val clouds: CloudsItem,
    val dt: Int,
    val sys: SysItem,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
) {
    @Serializable
    class Cord(
        val lon: Double,
        val lat: Double,
    )

    @Serializable
    class WeatherItem(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String,
    )

    @Serializable
    class MainItem(
        val temp: Double,
        val feels_like: Double,
        val temp_min: Double,
        val temp_max: Double,
        val pressure: Int,
        val humidity: Int,
        val sea_level: Int,
        val grnd_level: Int,
    )

    @Serializable
    class WindItem(
        val speed: Double,
        val deg: Int,
        val gust: Double,
    )

    @Serializable
    class CloudsItem(
        val all: Int,
    )

    @Serializable
    class SysItem(

        val type: Int,
        val id: Int,
        val country: String,
        val sunrise: Int,
        val sunset: Int,
    )
}