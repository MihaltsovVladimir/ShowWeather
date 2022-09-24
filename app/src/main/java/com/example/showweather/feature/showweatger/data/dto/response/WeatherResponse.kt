package com.example.showweather.feature.showweatger.data.dto.response

import com.google.gson.annotations.SerializedName


class WeatherResponse(

    @SerializedName("coord")
    val coord: Cord,
    val weather: List<WeatherItem>,
    @SerializedName("base")
    val base: String,
    val main: MainItem,
    @SerializedName("visibility")
    val visibility: Int,
    val wind: WindItem,
    val clouds: CloudsItem,
    @SerializedName("dt")
    val dt: Int,
    val sys: SysItem,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val cod: Int
) {
    class Cord(
        @SerializedName("lon")
        val lon: Double,
        @SerializedName("lat")
        val lat: Double
    )

    class WeatherItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String,
    )

    class MainItem(
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("feels_like")
        val feels_like: Double,
        @SerializedName("temp_min")
        val temp_min: Double,
        @SerializedName("temp_max")
        val temp_max: Double,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("sea_level")
        val sea_level: Int,
        @SerializedName("grnd_level")
        val grnd_level: Int
    )

    class WindItem(
        @SerializedName("speed")
        val speed: Double,
        @SerializedName("deg")
        val deg: Int,
        @SerializedName("gust")
        val gust: Double
    )

    class CloudsItem(
        @SerializedName("all")
        val all: Int
    )

    class SysItem(
        @SerializedName("type")
        val type: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("country")
        val country: String,
        @SerializedName("sunrise")
        val sunrise: Int,
        @SerializedName("sunset")
        val sunset: Int
    )
}
/*
{
  "coord": {
    "lon": 10.99,
    "lat": 44.34
  },
  "weather": [
    {
      "id": 501,
      "main": "Rain",
      "description": "moderate rain",
      "icon": "10d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 298.48,
    "feels_like": 298.74,
    "temp_min": 297.56,
    "temp_max": 300.05,
    "pressure": 1015,
    "humidity": 64,
    "sea_level": 1015,
    "grnd_level": 933
  },
  "visibility": 10000,
  "wind": {
    "speed": 0.62,
    "deg": 349,
    "gust": 1.18
  },
  "clouds": {
    "all": 100
  },
  "dt": 1661870592,
  "sys": {
    "type": 2,
    "id": 2075663,
    "country": "IT",
    "sunrise": 1661834187,
    "sunset": 1661882248
  },
  "timezone": 7200,
  "id": 3163858,
  "name": "Zocca",
  "cod": 200
}


 */