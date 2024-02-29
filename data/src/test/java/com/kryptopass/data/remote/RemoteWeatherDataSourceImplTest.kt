package com.kryptopass.data.remote

import com.kryptopass.data.remote.networking.WeatherService
import com.kryptopass.data.remote.networking.model.Coord
import com.kryptopass.data.remote.networking.model.WeatherModel
import com.kryptopass.data.remote.source.RemoteWeatherDataSourceImpl
import com.kryptopass.domain.entity.UseCaseException
import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.Wind
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteWeatherDataSourceImplTest {

    private val service = mock<WeatherService>()
    private val dataSource = RemoteWeatherDataSourceImpl(service)

    private val launchId = "Launch ID"
    private val flightNumber = 1

    @Test
    fun testGetWeatherList() = runTest {
        val lat = 100.0
        val lon = 100.0
        val remoteLaunches = listOf(
            WeatherModel(
                "Base", com.kryptopass.data.remote.networking.model.Clouds(), 1,
                Coord(lat, lon), 2, 3, com.kryptopass.data.remote.networking.model.Main(),
                "Name", com.kryptopass.data.remote.networking.model.Rain(),
                com.kryptopass.data.remote.networking.model.Sys(), 4, 5, listOf(),
                com.kryptopass.data.remote.networking.model.Wind()
            )
        )
        val expectedLaunches = listOf(
            Weather(
                "Base", Clouds(), 1, Coordinate(lat, lon), 2, 3, Main(),
                "Name", Rain(), Sys(), 4, 5, listOf(), Wind()
            )
        )

        whenever(service.getWeatherList()).thenReturn(remoteLaunches)
        val result = dataSource.getWeatherForLocationList().first()

        assertEquals(expectedLaunches, result)
    }

    @Test
    fun testGetWeather() = runTest {
        val lat = 100.0
        val lon = 100.0
        val remoteWeather = WeatherModel(
            "Base", com.kryptopass.data.remote.networking.model.Clouds(), 1,
            Coord(lat, lon), 2, 3, com.kryptopass.data.remote.networking.model.Main(),
            "Name", com.kryptopass.data.remote.networking.model.Rain(),
            com.kryptopass.data.remote.networking.model.Sys(), 4, 5, listOf(),
            com.kryptopass.data.remote.networking.model.Wind()
        )
        val expectedWeather = Weather(
            "Base", Clouds(), 1, Coordinate(lat, lon), 2, 3, Main(),
            "Name", Rain(), Sys(), 4, 5, listOf(), Wind()
        )

        whenever(service.getWeather(lat, lon)).thenReturn(remoteWeather)
        val result = dataSource.getWeatherForLocation(lat, lon).first()

        assertEquals(expectedWeather, result)
    }

    @Test
    fun testGetWeatherListThrowsError() = runTest {
        whenever(service.getWeatherList()).thenThrow(RuntimeException())

        dataSource.getWeatherForLocationList().catch {
            TestCase.assertTrue(it is UseCaseException.WeatherException)
        }
    }
}