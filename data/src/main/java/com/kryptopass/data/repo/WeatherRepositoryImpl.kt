package com.kryptopass.data.repo

import com.kryptopass.data.repo.source.local.LocalWeatherDataSource
import com.kryptopass.data.repo.source.remote.RemoteWeatherDataSource
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class WeatherRepositoryImpl(
    private val remoteSource: RemoteWeatherDataSource,
    private val localSource: LocalWeatherDataSource
): WeatherRepository {

    override fun getWeatherForLocationList(): Flow<List<Weather>> =
        remoteSource.getWeatherForLocationList().onEach {
            localSource.addWeatherForLocation(it)
        }

    override fun getWeatherByLocation(coordinate: Coordinate): Flow<Weather?> =
        remoteSource.getWeatherForLocation(coordinate.lat, coordinate.lon).onEach {
            localSource.addWeatherForLocation(listOf(it))
        }
}