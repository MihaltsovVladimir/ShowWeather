package com.example.showweather.feature.showweatger.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.showweather.databinding.ShowSpinnerFragmentBinding
import com.example.showweather.feature.showweatger.domain.model.ShowWeatherModel
import com.example.showweather.feature.showweatger.domain.model.entity.PointModelEntity
import com.example.showweather.feature.showweatger.presentation.adapter.MyCustomSpinnerAdapter
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import com.example.showweather.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShowWeatherFragment : BaseFragment<ShowWeatherViewModel, ShowSpinnerFragmentBinding>() {

    override val viewModel: ShowWeatherViewModel by activityViewModels()

    var adapter: MyCustomSpinnerAdapter? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ShowSpinnerFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun initForm() {
        viewModel.addCityToRoom(createInitLocality())
    }

    override fun onReadyToRequests() {
        super.onReadyToRequests()
        viewModel.getWeather()
    }

    override fun subscribe() {
        super.subscribe()
        launchAndRepeatWithViewLifecycle {
            viewModel.listWeatherStateFlow.collectNotNull() {
                Log.e("TAG", "subscribe:$it ")
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
                    var counter = 0 //todo
                    viewModel.listWeatherStateFlow.value?.listItems?.map {
                        it.isChecked = counter == position
                        counter++
                    }
                }
            }
        b.firstFragmentButton.setOnClickListener {
            viewModel.listWeatherStateFlow.value?.listItems?.forEach {
                it.isChecked
                Log.e("TAG", "onItemSelected: ${it.isChecked} ", )
            }
            Toast.makeText(
                context,
                "Немного терпения, мы работаем на дааным функционалом",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun populateForm(showModel: ShowWeatherModel) {
        if (adapter == null) {
            adapter = MyCustomSpinnerAdapter(requireContext(), showModel)
            b.firstFragmentSpinner.adapter = adapter
        }
    }

    private fun createInitLocality(): List<PointModelEntity> = listOf(
        PointModelEntity(UUID.randomUUID().toString(), "Gomel", "52.353917", "31.11178144"),
        PointModelEntity(UUID.randomUUID().toString(), "Minsk", "53.902284", "27.561831"),
        PointModelEntity(UUID.randomUUID().toString(), "Tokio", "49.088732", "33.413770")
    )
}