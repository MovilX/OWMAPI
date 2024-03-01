package com.kryptopass.data.remote.networking

import com.kryptopass.data.BuildConfig
import com.kryptopass.data.remote.networking.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    //@GET("2.5/weather?lat=25.77&lon=-80.19&appid=${BuildConfig.OWM_API_KEY}")
    //suspend fun getWeather(): WeatherModel

    // Zocca, Italy -- lat=44.34&lon=10.99
    // Dallas, TX -- lat=32.77&lon=-96.79
    // Atlanta, GA -- lat=33.74&lon=-84.38
    // Phoenix, AZ -- lat=33.44&lon=-112.07
    // London, UK -- lat=51.50&lon=-0.12
    // Chicago, IL -- lat=41.87&lon=-87.62
    // Miami, FL -- lat=25.77&lon=-80.19


    @GET("2.5/weather")
    suspend fun getWeatherByLocation(
        @Query("q") cityAndCountryCode: String?,
        @Query("appid") apiKey: String = BuildConfig.OWM_API_KEY
    ): WeatherModel
}