package com.example.showweather.cache

import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestDao {

    private val dao = DaoTest()

    @Before
    fun initialize() {
        val createInitLocality = mutableListOf(
            PointModelEntity("Gomel", "52.353917", "31.11178144", null, false),
            PointModelEntity("Minsk", "53.902284", "27.561831", null, false),
            PointModelEntity("Tokio", "49.088732", "33.413770", null, false)
        )
        dao.data = createInitLocality
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
        dao.insertCityAll(createInitLocality)
        val actual = dao.getPointModelAll().size

        //assert
        assertEquals(3, actual)
    }

    @Test
    fun `add one point`() {
        dao.insert(PointModelEntity("Gomel", "52.353917", "31.11178144", null, false))
        val actual = dao.getPointModelAll().size

        assertEquals(3, actual)
    }

    @Test
    fun `add new one point`() {
        dao.insert(PointModelEntity("Paris", "52.353917", "31.11178144", null, false))
        val actual = dao.getPointModelAll().size

        assertEquals(4, actual)
    }

    @Test
    fun `add new the same one point twice`() {
        dao.insert(PointModelEntity("Paris", "52.353917", "31.11178144", null, false))
        val actualData = PointModelEntity("Paris", "52.99999", "31.99999", null, false)
        dao.insert(actualData)
        val actual = dao.getPointModelAll().find { it.name == "Paris" } === actualData

        assertEquals(true, actual)
    }

    @Test
    fun `add the same point`() {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        dao.insert(newData)
        val actual = dao.getPointModelAll()[0] === newData

        assertEquals(true, actual)
    }

    @Test
    fun `add the same twice point`() {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        dao.insert(newData)
        val newDataTwo = PointModelEntity("Gomel", "52.55555", "31.99999", null, false)
        dao.insert(newDataTwo)
        val actual = dao.getPointModelAll()[0] === newDataTwo

        assertEquals(true, actual)
    }

    @Test
    fun `add the same twice no equals point`() {
        val newData = PointModelEntity("Gomel", "52.55555", "31.55555", null, false)
        dao.insert(newData)
        val newDataTwo = PointModelEntity("Gomel", "52.55555", "31.99999", null, false)
        dao.insert(newDataTwo)
        val actual = dao.getPointModelAll()[0] === newData

        assertEquals(false, actual)
    }
}