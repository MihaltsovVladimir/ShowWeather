package com.example.showweather

import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestDatabaseHelper {

    private val databaseHelper = DatabaseHelperTest(DaoTest())

    @Before
    fun initialize() = runBlocking {
        val createInitLocality = mutableListOf(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false),
            PointModelEntity("Minsk", "53.902284", "27.561831", null, false),
            PointModelEntity("Tokio", "49.088732", "33.413770", null, false)
        )
        databaseHelper.insertCityAll(createInitLocality)
    }

    @Test
    fun getThreePoint() = runBlocking {
        //arrange
        val createInitLocality: List<PointModelEntity> = listOf(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false),
            PointModelEntity("Minsk", "53.902284", "27.561831", null, false),
            PointModelEntity("Tokio", "49.088732", "33.413770", null, false)
        )

        //act
        databaseHelper.insertCityAll(createInitLocality)
        val actual = databaseHelper.getPointModelAll().size

        //assert
        assertEquals(3, actual)
    }

    @Test
    fun savePositionSizeOneItemDataBase() = runBlocking {
        val checkedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        databaseHelper.savePositionSpinner(checkedItem)
        val actual = databaseHelper.getPointModelAll().filter { it.isChecked }.size

        assertEquals(1, actual)
    }

    @Test
    fun savePositionSizeShouldBeOneItemODataBase() = runBlocking {
        val checkedItem = PointModelEntity("Gomel", "52.353917", "31.11178144", null, true)
        databaseHelper.savePositionSpinner(checkedItem)
        val newCheckedItem = PointModelEntity("Minsk", "53.902284", "27.561831", null, true)
        databaseHelper.savePositionSpinner(newCheckedItem)
        val actual = databaseHelper.getPointModelAll().filter { it.isChecked }.size

        assertEquals(1, actual)
    }

    @Test
    fun addOnePointToDataBase() = runBlocking {

        databaseHelper.insertData(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false)
        )
        val actual = databaseHelper.getPointModelAll().size

        assertEquals(3, actual)
    }

    @Test
    fun addOnePointNewToDataBase() = runBlocking {
        databaseHelper.insertData(
            PointModelEntity(
                "Paris", "52.353917", "31.11178144", null, false
            )
        )
        val actual = databaseHelper.getPointModelAll().size

        assertEquals(4, actual)
    }

    @Test
    fun addTheSameOnePointNewTwiceToDataBase() = runBlocking {
        databaseHelper.insertData(
            PointModelEntity(
                "Paris", "52.353917", "31.11178144", null, false
            )
        )
        val actualData = PointModelEntity("Paris", "52.99999", "31.99999", null, false)
        databaseHelper.insertData(actualData)
        val actual = databaseHelper.getPointModelAll().find { it.name == "Paris" } === actualData

        assertEquals(true, actual)
    }

    @Test
    fun addTheSamePointToDataBase() = runBlocking {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        databaseHelper.insertData(newData)
        val actual = databaseHelper.getPointModelAll()[0] === newData
        assertEquals(true, actual)
    }

    @Test
    fun addTheSameTwicePointToDataBase() = runBlocking {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        databaseHelper.insertData(newData)
        val newDataTwo = PointModelEntity("Gomel", "52.55555", "31.99999", null, false)
        databaseHelper.insertData(newDataTwo)
        val actual = databaseHelper.getPointModelAll()[0] === newDataTwo

        assertEquals(true, actual)
    }

    @Test
    fun addTheSameTwiceNoEqualsPointToDataBase() = runBlocking {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        databaseHelper.insertData(newData)
        val newDataTwo = PointModelEntity("Gomel", "52.55555", "31.99999", null, false)
        databaseHelper.insertData(newDataTwo)
        val actual = databaseHelper.getPointModelAll()[0] === newData

        assertEquals(false, actual)
    }
}