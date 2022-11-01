package com.example.showweather.cloud

import com.example.showweather.cache.DaoTest
import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.cloud.CloudDataSource
import com.example.showweather.feature.showweatger.data.cloud.ws.MockWeatherApiService
import com.example.showweather.feature.showweatger.data.dto.mapper.map
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCloudDataSource {


    private val apiService = MockWeatherApiService()
    private val helper = DatabaseHelper.Base(DaoTest())
    private val dataSource = CloudDataSource.Base(apiService, helper)
    private val gomel = PointModelEntity("Gomel", "52.353917", "31.11178144", null, false)
    private val minsk = PointModelEntity("Minsk", "53.902284", "27.561831", null, false)
    private val tokio = PointModelEntity("Tokio", "49.088732", "33.413770", null, false)

    @Before
    fun initialize() = runBlocking {

    }

    @Test
    fun `test weather`() = runBlocking {

        helper.insertData(
            apiService.getWeather("52.353917", "31.11178144", "apiKey").map("Gomel", false)
        )
        val actual = dataSource.getWeather("apiKey").listItems.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `test the same name weather`() = runBlocking {
        helper.insertData(
            apiService.getWeather("52.353917", "31.11178144", "apiKey").map("Gomel", false)
        )
        val actual = dataSource.getWeather("apiKey").listItems[0].name
        val expected = "Gomel"
        assertEquals(expected, actual)
    }

    @Test
    fun `test add two item weather`() = runBlocking {
        helper.insertData(
            apiService.getWeather("52.353917", "31.11178144", "apiKey").map("Gomel", false)
        )
        helper.insertData(
            apiService.getWeather("52.353917", "31.11178144", "apiKey").map("Minsk", false)
        )
        val actual = dataSource.getWeather("apiKey").listItems.size
        val expected = 2
        assertEquals(expected, actual)
    }
}