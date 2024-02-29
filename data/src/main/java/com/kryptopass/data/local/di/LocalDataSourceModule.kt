package com.kryptopass.data.local.di

import com.kryptopass.data.local.source.LocalWeatherDataSourceImpl
import com.kryptopass.data.repo.source.local.LocalWeatherDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindWeatherDataSource(dataSource: LocalWeatherDataSourceImpl): LocalWeatherDataSource
}