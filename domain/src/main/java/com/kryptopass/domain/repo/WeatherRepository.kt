package com.kryptopass.domain.repo

import com.kryptopass.domain.entity.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherList(): Flow<List<Weather>>

    fun getWeatherByCityAndCountryCode(cityAndCountryCode: String): Flow<Weather?>
}