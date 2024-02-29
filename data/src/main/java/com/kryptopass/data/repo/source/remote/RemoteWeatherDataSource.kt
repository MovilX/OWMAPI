package com.kryptopass.data.repo.source.remote

import com.kryptopass.domain.entity.weather.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteWeatherDataSource {

    fun getWeatherForLocationList(): Flow<List<Weather>>

    fun getWeatherForLocation(lat: Double?, lon: Double?): Flow<Weather>
}