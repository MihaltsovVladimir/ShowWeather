package com.example.showweather.feature.showweatger.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.showweather.databinding.ShowSpinnerActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowSpinnerActivity: AppCompatActivity()  {

//    private var _binding: ShowSpinnerActivityBinding? = null
//    val b get() = _binding!!
    private lateinit var binding: ShowSpinnerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowSpinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}