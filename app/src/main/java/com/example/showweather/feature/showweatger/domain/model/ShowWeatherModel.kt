package com.example.showweather.feature.showweatger.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowWeatherModel(

    val listItems: List<CityItemModel>
): Parcelable