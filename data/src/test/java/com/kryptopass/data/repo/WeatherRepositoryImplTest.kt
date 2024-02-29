package com.kryptopass.data.repo

import com.kryptopass.data.repo.source.local.LocalWeatherDataSource
import com.kryptopass.data.repo.source.remote.RemoteWeatherDataSource
import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.Wind
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class WeatherRepositoryImplTest {

    private val remoteSource = mock<RemoteWeatherDataSource>()
    private val localSource = mock<LocalWeatherDataSource>()
    private val repo = WeatherRepositoryImpl(remoteSource, localSource)

    private val city = "Zocca"

    @Test
    fun testGetWeatherList() = runTest {
        val weatherList = listOf(
            Weather(
                "Base1", Clouds(), 1, Coordinate(), 2, 3, Main(), city,
                Rain(), Sys(), 4, 5, listOf(), Wind()
            )
        )

        whenever(remoteSource.getWeather()).thenReturn(flowOf(weatherList))
        val result = repo.getWeatherList().first()

        assertEquals(weatherList, result)
        verify(localSource).addWeatherForCityAndCountryCode(weatherList)
    }

    @Test
    fun testGetWeather() = runTest {
        val weather = Weather(
            "Base1", Clouds(), 1, Coordinate(), 2, 3, Main(), city,
            Rain(), Sys(), 4, 5, listOf(), Wind()
        )

        whenever(remoteSource.getWeatherCityAndCountryCode(city)).thenReturn(flowOf(weather))
        val result = repo.getWeatherByCityAndCountryCode(city).first()

        assertEquals(weather, result)
        verify(localSource).addWeatherForCityAndCountryCode(listOf(weather))
    }
}