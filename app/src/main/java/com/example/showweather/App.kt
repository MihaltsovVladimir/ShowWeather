package com.example.showweather

import android.app.Application
import android.content.Context
import com.example.showweather.feature.showweatger.data.di.ContextProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), ContextProvider {

    override fun getModifiedApplicationContext(): Context  = applicationContext

}
