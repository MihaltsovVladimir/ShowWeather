package com.example.showweather.feature.base.data.di

import android.content.Context

interface ContextProvider {

    fun getModifiedApplicationContext(): Context
}