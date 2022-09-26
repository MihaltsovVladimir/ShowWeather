package com.example.showweather.feature.base.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.showweather.databinding.ShowSpinnerActivityBinding

abstract class BaseActivity: AppCompatActivity() {

    private lateinit var binding: ShowSpinnerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowSpinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}