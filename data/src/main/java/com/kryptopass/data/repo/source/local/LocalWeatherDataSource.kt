package com.kryptopass.data.repo.source.local

import com.kryptopass.domain.entity.weather.Weather
import kotlinx.coroutines.flow.Flow

interface LocalWeatherDataSource {

    fun getWeatherList(): Flow<List<Weather>>

    suspend fun addWeatherForCityAndCountryCode(weathers: List<Weather>)
}