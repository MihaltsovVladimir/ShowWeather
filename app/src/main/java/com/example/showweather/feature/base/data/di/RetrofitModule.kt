package com.example.showweather.feature.base.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofitBuilder(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl("https://api.openweathermap.org/data/2.5/")


    @Provides
    @Singleton
    fun provideRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit = builder
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(gsonBuilder: GsonBuilder): Gson {
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }
}