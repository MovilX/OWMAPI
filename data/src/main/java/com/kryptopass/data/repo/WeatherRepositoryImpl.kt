package com.kryptopass.data.repo

import com.kryptopass.data.repo.source.local.LocalWeatherDataSource
import com.kryptopass.data.repo.source.remote.RemoteWeatherDataSource
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class WeatherRepositoryImpl(
    private val remoteSource: RemoteWeatherDataSource,
    private val localSource: LocalWeatherDataSource
): WeatherRepository {

    override fun getWeatherList(): Flow<List<Weather>> =
        localSource.getWeatherList()

    override fun getWeatherByCityAndCountryCode(cityAndCountryCode: String): Flow<Weather?> =
        remoteSource.getWeatherCityAndCountryCode(cityAndCountryCode).onEach {
            localSource.addWeatherForCityAndCountryCode(listOf(it))
        }
}