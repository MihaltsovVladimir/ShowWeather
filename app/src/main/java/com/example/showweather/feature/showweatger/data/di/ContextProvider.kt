package com.example.showweather.feature.showweatger.data.di

import android.content.Context

interface ContextProvider {

    fun getModifiedApplicationContext(): Context
}