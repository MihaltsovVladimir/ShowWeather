package com.example.showweather.feature.base.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofitBuilder(
        jsonConverterFactory: Converter.Factory,
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(jsonConverterFactory)
        .baseUrl("https://api.openweathermap.org/data/2.5/")

    @Provides
    @Singleton
    fun provideRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
    ): Retrofit = builder
        .client(okHttpClient)
        .build()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideJsonConverter(json: Json) =
        json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideJson() =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }
}