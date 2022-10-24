package com.example.showweather.feature.base.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.FrameLayout
import com.example.showweather.databinding.SpinnerViewBinding
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity

class SpinnerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {


    val b by lazy { SpinnerViewBinding.inflate(LayoutInflater.from(context), this, true) }
    private var adapter: MyCustomSpinnerAdapter? = null

    var checkedItem: (PointModelEntity) -> Unit = {}

    var model: ShowWeatherModel? = null

    init {
        setListeners()
    }

    fun populate(model: ShowWeatherModel) {
        this.model = model
        if (adapter == null) {
            adapter = context?.let { MyCustomSpinnerAdapter(it, model) }
            b.spinner.adapter = adapter
        } else adapter?.updateData(model)
        b.spinner.setSelection(model.getIndexCheckedItem(), true)
        b.stateTextView.text = "model.dataTime"
    }

    private fun setListeners() {
        b.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    model?.getItem(position)?.let { checkedItem(it) }
                    model?.setCheckToItem(position)
                }
            }
    }
}