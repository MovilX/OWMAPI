package com.kryptopass.owmapi.di

import com.kryptopass.data.repo.WeatherRepositoryImpl
import com.kryptopass.data.repo.source.local.LocalWeatherDataSource
import com.kryptopass.data.repo.source.remote.RemoteWeatherDataSource
import com.kryptopass.domain.repo.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        remoteSource: RemoteWeatherDataSource,
        localSource: LocalWeatherDataSource
    ): WeatherRepository = WeatherRepositoryImpl(
        remoteSource,
        localSource
    )
}