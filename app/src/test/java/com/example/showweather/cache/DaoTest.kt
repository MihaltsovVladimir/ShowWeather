package com.example.showweather.cache

import com.example.showweather.feature.showweatger.data.cache.WeatherDao
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity

class DaoTest : WeatherDao {

    var data = mutableListOf<PointModelEntity>()

    override fun getPointModelAll(): List<PointModelEntity> = data

    override fun insert(point: PointModelEntity) {
        data.replaceAll { oldPoint -> if (oldPoint.name == point.name) point else oldPoint }
        if (!data.contains(point)) data.add(point)
    }

    override fun insertCityAll(points: List<PointModelEntity>) {
        points.forEach { point ->
            if (data.contains(point)) data.replaceAll { it }
            else data.add(point)
        }
    }
}