package com.example.showweather.feature.showweatger.domain.model

import android.os.Parcelable
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowWeatherModel(

    val listItems: List<PointModelEntity>,
) : Parcelable {

    fun getIndexCheckedItem(): Int = listItems.indexOf(listItems.find { it.isChecked })

    fun setCheckToItem(position: Int) {
        listItems.mapIndexed { index, pointModelEntity ->
            pointModelEntity.isChecked = index == position
        }
    }

    fun getItem(position: Int): PointModelEntity = listItems[position]
}