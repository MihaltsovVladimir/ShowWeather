package com.example.showweather.feature.searchcity.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.showweather.databinding.SearchFragmentBinding
import com.example.showweather.feature.searchcity.presentation.viewmodel.SearchCityViewModel
import com.example.showweather.feature.base.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchCityViewModel, SearchFragmentBinding>() {

    override val viewModel: SearchCityViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SearchFragmentBinding.inflate(inflater, container, false)
}