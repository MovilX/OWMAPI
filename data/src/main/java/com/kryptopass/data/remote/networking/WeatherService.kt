package com.kryptopass.data.remote.networking

import com.kryptopass.data.BuildConfig
import com.kryptopass.data.remote.networking.model.WeatherModel
import retrofit2.http.GET

interface WeatherService {

    // TODO: get current location for user first opening app
    // TODO: get last searched for user navigation
    // TODO: inject lat/lon
    @GET("2.5/weather?lat=44.34&lon=10.99&appid=${BuildConfig.OWM_API_KEY}")
    suspend fun getWeatherList(): List<WeatherModel>

    @GET("2.5/weather?lat=44.34&lon=10.99&appid=${BuildConfig.OWM_API_KEY}")
    suspend fun getWeather(lat: Double?, lon: Double?): WeatherModel
}