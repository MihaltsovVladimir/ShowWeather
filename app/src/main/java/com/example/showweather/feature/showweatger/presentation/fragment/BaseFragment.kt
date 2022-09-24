package com.example.showweather.feature.showweatger.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.showweather.feature.showweatger.presentation.viewmodel.ShowWeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : ShowWeatherViewModel, VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val b get() = _binding!!

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createViewBinding(inflater, container)
        return b.root
    }


    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initForm()
        subscribe()
        setListeners()
    }

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    open fun initForm(){}

    open fun onReadyToRequests() {}

    @CallSuper
    open fun subscribe() {
        lifecycleScope.launchWhenStarted {
            onReadyToRequests()
        }
    }

    open fun setListeners(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    suspend fun <T : Any> Flow<T?>.collectNotNull(collector: FlowCollector<T>) {
        filterNotNull().collect(collector)
    }
}
