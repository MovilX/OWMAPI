package com.kryptopass.data.local.source

import com.kryptopass.data.local.db.weather.WeatherDao
import com.kryptopass.data.local.db.weather.WeatherEntity
import com.kryptopass.data.repo.source.local.LocalWeatherDataSource
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.WeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalWeatherDataSourceImpl @Inject constructor(
    private val dao: WeatherDao
) : LocalWeatherDataSource {

    override fun getWeatherList(): Flow<List<Weather>> =
        dao.getWeatherForLocations().map {
            it.map { model ->
                Weather(
                    model.base, model.clouds, model.cod, model.coordinate, model.dt,
                    model.weatherId, model.main, model.name, model.rain, model.sys,
                    model.timezone, model.visibility, listOf(WeatherItem()), model.wind
                )
            }
        }

    override suspend fun addWeatherForCityAndCountryCode(weathers: List<Weather>) =
        dao.insertWeatherList(weathers.map { weather ->
            WeatherEntity(
                weather.name, weather.base, weather.clouds, weather.cod, weather.coordinate,
                weather.dt, weather.id, weather.main, weather.rain,
                weather.sys, weather.timezone, weather.visibility, listOf(), weather.wind
            )
        })
}