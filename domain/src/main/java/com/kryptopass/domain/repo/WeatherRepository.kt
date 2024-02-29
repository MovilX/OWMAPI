package com.kryptopass.domain.repo

import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherForLocationList(): Flow<List<Weather>>

    // TODO: get location by coordinate (search coordinates) - last searched location (intent)
    // TODO: get weather for current location: use google services? on load (first launch)
    fun getWeatherByLocation(coordinate: Coordinate): Flow<Weather?>
}