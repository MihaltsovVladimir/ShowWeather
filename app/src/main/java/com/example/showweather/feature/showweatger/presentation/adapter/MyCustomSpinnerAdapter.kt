package com.example.showweather.feature.showweatger.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.showweather.R
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel

class MyCustomSpinnerAdapter(

    private val context: Context,
    private var weatherModel: ShowWeatherModel
) : BaseAdapter() {

    fun updateData(newWeatherModel: ShowWeatherModel) {
        weatherModel = newWeatherModel
        notifyDataSetChanged()
    }

    override fun getCount(): Int = weatherModel.listItems.size

    override fun getItem(p0: Int): Any = weatherModel.listItems[p0]

    override fun getItemId(p0: Int): Long = 0

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var mConvertView = p1
        if (mConvertView == null) {
            val inflater = LayoutInflater.from(context)
            mConvertView = inflater.inflate(R.layout.spinner_item, p2, false)
        }
        val nameCity = mConvertView?.findViewById(R.id.name_city) as TextView
        nameCity.text = weatherModel.listItems[p0].name
        val weatherCity = mConvertView.findViewById(R.id.weather_city) as TextView
        weatherCity.text = weatherModel.listItems[p0].temperature
        return mConvertView
    }
}