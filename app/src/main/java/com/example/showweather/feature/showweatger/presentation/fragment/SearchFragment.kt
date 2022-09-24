package com.example.showweather.feature.showweatger.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.showweather.R
import com.example.showweather.databinding.SearchFragmentBinding
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<ShowWeatherViewModel, SearchFragmentBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SearchFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.back.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_showFragment)
        }
    }

    override val viewModel: ShowWeatherViewModel
        get() = TODO("Not yet implemented")
}