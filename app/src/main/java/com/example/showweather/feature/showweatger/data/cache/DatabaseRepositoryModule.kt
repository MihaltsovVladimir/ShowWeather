package com.example.showweather.feature.showweatger.data.cache

import com.example.showweather.feature.showweatger.data.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseRepositoryModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(helper: DatabaseHelper.Base) = CacheDataSource.Base(helper)
}