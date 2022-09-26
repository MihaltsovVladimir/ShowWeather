package com.example.showweather.feature.showweatger.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.showweather.databinding.SearchFragmentBinding
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<ShowWeatherViewModel, SearchFragmentBinding>() {

    override val viewModel: ShowWeatherViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SearchFragmentBinding.inflate(inflater, container, false)
}