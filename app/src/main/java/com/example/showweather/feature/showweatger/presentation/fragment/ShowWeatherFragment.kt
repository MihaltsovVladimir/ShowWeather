package com.example.showweather.feature.showweatger.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.PopupWindow
import androidx.fragment.app.activityViewModels
import com.example.showweather.R
import com.example.showweather.databinding.ShowSpinnerFragmentBinding
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.presentation.adapter.CountryAdapter
import com.example.showweather.feature.showweatger.presentation.adapter.MyCustomSpinnerAdapter
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import com.example.showweather.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShowWeatherFragment : BaseFragment<ShowWeatherViewModel, ShowSpinnerFragmentBinding>() {

    override val viewModel: ShowWeatherViewModel by activityViewModels()

    var adapter: MyCustomSpinnerAdapter? = null
    var adapterCountry: CountryAdapter? = null
    var popupWindow: PopupWindow? = null


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ShowSpinnerFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun initForm() {
        val listCity = listOf(
            PointModelEntity(UUID.randomUUID().toString(), "Gomel", "52.353917", "31.11178144"),
            PointModelEntity(UUID.randomUUID().toString(), "Minsk", "53.902284", "27.561831"),
            PointModelEntity(UUID.randomUUID().toString(), "Tokio", "49.088732", "33.413770")
        )
        viewModel.addCityToRoom(listCity)
    }

    override fun onReadyToRequests() {
        super.onReadyToRequests()
        viewModel.getWeather()
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
        b.firstFragmentSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }
            }
//        b.sCountry.setOnClickListener {
//            popupWindow?.dismiss()
//            if (popupWindow == null) {
//                provideCountryPopupWindow(it)
//            } popupWindow!!.showAsDropDown(it, 0, -it.height)
//        }
        b.firstFragmentButton.setOnClickListener {
//            Toast.makeText(context, UUID.randomUUID().toString(), Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_showFragment_to_searchFragment)
        }
    }

    private fun populateForm(showModel: ShowWeatherModel) {
        adapterCountry = CountryAdapter(requireContext(),showModel)
        b.sCountry.adapter = adapter
        if (adapter == null) {
            adapter = MyCustomSpinnerAdapter(requireContext(), showModel)
            b.firstFragmentSpinner.adapter = adapter
        }
    }


    private fun provideCountryPopupWindow(it: View) {
        popupWindow = PopupWindow(it.width, ViewGroup.LayoutParams.WRAP_CONTENT)
            .apply {
                val backgroundDrawable = requireActivity().getDrawable(
                    R.drawable.blue_outline_white_background)
                setBackgroundDrawable(backgroundDrawable)
                isOutsideTouchable = true
                val listView = layoutInflater.inflate(
                    R.layout.layout_country_dropdown,
                    null,
                    false) as ListView
                listView.adapter = adapterCountry
                listView.setOnItemClickListener { _, _, position, _ ->
                    val selectedCountry = adapterCountry!!.getItem(position)!!
//                    viewModel.setLegalCountry(1)  //todo saved position
                    popupWindow?.dismiss()
                }
                contentView = listView
            }
    }
}