package com.kryptopass.data.remote.source

import com.kryptopass.data.remote.networking.WeatherService
import com.kryptopass.data.remote.networking.model.Clouds
import com.kryptopass.data.remote.networking.model.Coord
import com.kryptopass.data.remote.networking.model.Main
import com.kryptopass.data.remote.networking.model.Rain
import com.kryptopass.data.remote.networking.model.Sys
import com.kryptopass.data.remote.networking.model.WeatherModel
import com.kryptopass.data.remote.networking.model.Wind
import com.kryptopass.data.repo.source.remote.RemoteWeatherDataSource
import com.kryptopass.domain.entity.UseCaseException
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.WeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteWeatherDataSourceImpl @Inject constructor(
    private val service: WeatherService
) : RemoteWeatherDataSource {

    override fun getWeatherForLocationList(): Flow<List<Weather>> = flow {
        emit(service.getWeatherList())
    }.map { coins ->
        coins.map { apiModel ->
            convert(apiModel)
        }
    }.catch {
        throw UseCaseException.WeatherException(it)
    }

    override fun getWeatherForLocation(lat: Double?, lon: Double?): Flow<Weather> = flow {
        emit(service.getWeather(lat, lon))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.WeatherException(it)
    }

    private fun convert(model: WeatherModel) =
        Weather(
            model.base,
            convertClouds(model.clouds),
            model.cod,
            convertCoord(model.coord),
            model.dt,
            model.id,
            convertMain(model.main),
            model.name,
            convertRain(model.rain),
            convertSys(model.sys),
            model.timezone,
            model.visibility,
            convertWeather(model.weather),
            convertWind(model.wind)
        )

    private fun convertClouds(clouds: Clouds?) = com.kryptopass.domain.entity.weather.Clouds(
        clouds?.all
    )

    private fun convertCoord(coord: Coord?) = com.kryptopass.domain.entity.weather.Coordinate(
        coord?.lat, coord?.lon
    )

    private fun convertMain(main: Main?) = com.kryptopass.domain.entity.weather.Main(
        main?.feelsLike, main?.grndLevel, main?.humidity, main?.pressure, main?.seaLevel,
        main?.temp, main?.tempMax, main?.tempMin
    )

    private fun convertRain(rain: Rain?) = com.kryptopass.domain.entity.weather.Rain(
        rain?.h
    )

    private fun convertSys(sys: Sys?) = com.kryptopass.domain.entity.weather.Sys(
        sys?.country, sys?.id, sys?.sunrise, sys?.sunset, sys?.type
    )

    private fun convertWeather(weather: List<com.kryptopass.data.remote.networking.model.Weather?>?) =
        weather?.map {
            WeatherItem(
                it?.description, it?.icon, it?.id, it?.main
            )
    }

    private fun convertWind(wind: Wind?) = com.kryptopass.domain.entity.weather.Wind(
        wind?.deg, wind?.speed
    )
}