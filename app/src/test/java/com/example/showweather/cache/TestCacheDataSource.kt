package com.example.showweather.cache

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import com.example.showweather.feature.showweatger.data.cache.CacheDataSource
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCacheDataSource {

    private val helper = DatabaseHelper.Base(DaoTest())
    private val dataSource = CacheDataSource.Base(helper)

    @Before
    fun initialize() = runBlocking {
        val createInitLocality = mutableListOf(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false),
            PointModelEntity("Minsk", "53.902284", "27.561831", null, false),
            PointModelEntity("Tokio", "49.088732", "33.413770", null, false)
        )
        dataSource.insertPointAll(createInitLocality)
    }

    @Test
    fun `get three point`() = runBlocking {
        //arrange
        val createInitLocality: List<PointModelEntity> = listOf(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false),
            PointModelEntity("Minsk", "53.902284", "27.561831", null, false),
            PointModelEntity("Tokio", "49.088732", "33.413770", null, false)
        )

        //act
        dataSource.insertPointAll(createInitLocality)
        val actual = dataSource.getPointModelAll().size

        //assert
        val expected = 3
        assertEquals(expected, actual)
    }

    @Test
    fun `save position size one item`() = runBlocking {
        val checkedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        dataSource.savePositionSpinner(checkedItem)
        val actual = dataSource.getPointModelAll().filter { it.isChecked }.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `save position size should be one item`() = runBlocking {
        val checkedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        dataSource.savePositionSpinner(checkedItem)
        val newCheckedItem = PointModelEntity("Minsk", "53.902284", "27.561831", null, true)
        dataSource.savePositionSpinner(newCheckedItem)
        val actual = dataSource.getPointModelAll().filter { it.isChecked }.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `save position size add same item`() = runBlocking {
        val checkedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        dataSource.savePositionSpinner(checkedItem)
        val newCheckedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        dataSource.savePositionSpinner(newCheckedItem)
        val actual = dataSource.getPointModelAll().filter { it.isChecked }.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `save position size add same item name`() = runBlocking {
        val checkedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        dataSource.savePositionSpinner(checkedItem)
        val newCheckedItem = PointModelEntity("Minsk", "52.353917", "31.11178144", null, true)
        dataSource.savePositionSpinner(newCheckedItem)
        val actual = dataSource.getPointModelAll().filter { it.isChecked }[0].name
        val expected = "Minsk"
        assertEquals(expected, actual)
    }

    @Test
    fun `add one point`() = runBlocking {

        dataSource.insertData(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false)
        )
        val actual = dataSource.getPointModelAll().size
        val expected = 3
        assertEquals(expected, actual)
    }

    @Test
    fun `add one point new`() = runBlocking {
        dataSource.insertData(
            PointModelEntity(
                "Paris", "52.353917", "31.11178144", null, false
            )
        )
        val actual = dataSource.getPointModelAll().size
        val expected = 4
        assertEquals(expected, actual)
    }

    @Test
    fun `add the same one point new twice`() = runBlocking {
        dataSource.insertData(
            PointModelEntity(
                "Paris", "52.353917", "31.11178144", null, false
            )
        )
        val actualData = PointModelEntity("Paris", "52.99999", "31.99999", null, false)
        dataSource.insertData(actualData)
        val actual = dataSource.getPointModelAll().find { it.name == "Paris" } === actualData
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun `add the same point`() = runBlocking {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        dataSource.insertData(newData)
        val actual = dataSource.getPointModelAll()[0] === newData
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun `add the same twice point`() = runBlocking {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        dataSource.insertData(newData)
        val newDataTwo = PointModelEntity("Gomel", "52.55555", "31.99999", null, false)
        dataSource.insertData(newDataTwo)
        val actual = dataSource.getPointModelAll()[0] === newDataTwo
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun `add the same twice no equals`() = runBlocking {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        dataSource.insertData(newData)
        val newDataTwo = PointModelEntity("Gomel", "52.55555", "31.99999", null, false)
        dataSource.insertData(newDataTwo)
        val actual = dataSource.getPointModelAll()[0] === newData
        val expected = false
        assertEquals(expected, actual)
    }
}