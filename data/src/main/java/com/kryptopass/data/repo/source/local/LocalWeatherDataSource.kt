package com.kryptopass.data.repo.source.local

import com.kryptopass.domain.entity.weather.Weather
import kotlinx.coroutines.flow.Flow

interface LocalWeatherDataSource {

    fun getWeatherForLocationList(): Flow<List<Weather>>

    suspend fun addWeatherForLocation(weathers: List<Weather>)
}