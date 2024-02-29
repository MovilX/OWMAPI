package com.kryptopass.data.remote.di

import com.kryptopass.data.remote.source.RemoteWeatherDataSourceImpl
import com.kryptopass.data.repo.source.remote.RemoteWeatherDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindWeatherDataSource(datasource: RemoteWeatherDataSourceImpl): RemoteWeatherDataSource
}