package com.example.showweather.feature.showweatger.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherDatabaseModule {

    private const val WEATHER_DATABASE_NAME: String = "WEATHER_DATABASE_NAME"

    @Provides
    @Singleton
    fun provideDesktopDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            WEATHER_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideDesktopDao(db: WeatherDatabase) = db.getWeather()
}
