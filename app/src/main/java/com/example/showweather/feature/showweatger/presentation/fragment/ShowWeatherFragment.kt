package com.example.showweather.feature.showweatger.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.example.showweather.databinding.ShowSpinnerFragmentBinding
import com.example.showweather.feature.base.presentation.fragment.BaseFragment
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.presentation.adapter.MyCustomSpinnerAdapter
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import com.example.showweather.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowWeatherFragment : BaseFragment<ShowWeatherViewModel, ShowSpinnerFragmentBinding>() {

    override val viewModel: ShowWeatherViewModel by activityViewModels()

    var adapter: MyCustomSpinnerAdapter? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ShowSpinnerFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onReadyToRequests() {
        super.onReadyToRequests()
        viewModel.getWeather()
    }

    private fun initView() {
        viewModel.getDataFromDatabase()
    }

    override fun subscribe() {
        super.subscribe()
        launchAndRepeatWithViewLifecycle {
            viewModel.listWeatherStateFlow.collectNotNull() {
                Log.e("TAG", "subscribe: _____________________________________")
                populateForm(it)
            }
        }
    }

    override fun setListeners() {
        b.firstFragmentSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    viewModel.savePositionSpinner(position)
                }
            }
        b.firstFragmentButton.setOnClickListener {

        }
    }

    private fun populateForm(showModel: ShowWeatherModel) {
        if (adapter == null) {
            adapter = context?.let { MyCustomSpinnerAdapter(it, showModel) }
            b.firstFragmentSpinner.adapter = adapter
        } else adapter?.updateData(showModel)
    }
}