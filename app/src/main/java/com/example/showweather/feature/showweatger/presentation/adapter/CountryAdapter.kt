package com.example.showweather.feature.showweatger.presentation.adapter

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.showweather.R
import com.example.showweather.feature.showweatger.domain.model.CityItemModel
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel

class CountryAdapter(

    context: Context,
    private val weatherModel: ShowWeatherModel
) : ArrayAdapter<CityItemModel>(context, 0) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_country, parent, false)
        } else {
            view = convertView
        }
        getItem(position)?.let {
            setItemForCountry(view, it)
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (position == 0) {
            view = layoutInflater.inflate(R.layout.header_country, parent, false)
            view.setOnClickListener {
                val root = parent.rootView
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
            }
        } else {
            view = layoutInflater.inflate(R.layout.item_country_dropdown, parent, false)
            getItem(position)?.let { country ->
                setItemForCountry(view, country)
            }
        }
        return view
    }

    override fun getItem(position: Int): CityItemModel? {
//        if (position == 0) {
//            return null
//        }
        return weatherModel.listItems[position]
    }

//    override fun getCount() = super.getCount() + 1
    override fun getCount() = weatherModel.listItems.size

    override fun isEnabled(position: Int) = position != 0

    private fun setItemForCountry(view: View, point: CityItemModel) {
        val tvCountry = view.findViewById<TextView>(R.id.tvCountry)
        tvCountry.text = point.cityPoint?.name
    }
}