package com.kryptopass.data.repo.source.remote

import com.kryptopass.domain.entity.weather.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteWeatherDataSource {

    fun getWeather(): Flow<List<Weather>>

    fun getWeatherCityAndCountryCode(cityAndCountryCode: String?): Flow<Weather>
}