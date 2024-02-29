package com.kryptopass.owmapi.di

import com.kryptopass.domain.repo.WeatherRepository
import com.kryptopass.domain.usecase.GetWeatherForLocationListUseCase
import com.kryptopass.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetWeatherForLocationListUseCase(
        configuration: UseCase.Configuration,
        weatherRepo: WeatherRepository
    ): GetWeatherForLocationListUseCase = GetWeatherForLocationListUseCase(
        configuration,
        weatherRepo
    )
}