package com.example.showweather.feature.showweatger.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.showweather.databinding.ShowSpinnerFragmentBinding
import com.example.showweather.feature.base.presentation.fragment.BaseFragment
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import com.example.showweather.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowWeatherFragment : BaseFragment<ShowWeatherViewModel, ShowSpinnerFragmentBinding>() {

    override val viewModel: ShowWeatherViewModel by activityViewModels()

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
                populateForm(it)
            }
        }
    }

    override fun setListeners() {
        b.firstFragmentSpinner.checkedItem = { viewModel.savePositionSpinner(it) }
        b.firstFragmentButton.setOnClickListener {}
    }

    private fun populateForm(model: ShowWeatherModel) {
        b.firstFragmentSpinner.populate(model)
    }
}