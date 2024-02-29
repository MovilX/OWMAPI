package com.kryptopass.data.local

import com.kryptopass.data.local.db.weather.WeatherDao
import com.kryptopass.data.local.db.weather.WeatherEntity
import com.kryptopass.data.local.source.LocalWeatherDataSourceImpl
import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.WeatherItem
import com.kryptopass.domain.entity.weather.Wind
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalWeatherDataSourceImplTest {

    private val dao = mock<WeatherDao>()
    private val dataSource = LocalWeatherDataSourceImpl(dao)

    @Test
    fun testGetWeatherList() = runTest {
        val lat = 100.0
        val lon = 100.0
        val weatherList = listOf(
            WeatherEntity(
                null, "Base1", Clouds(), 1, Coordinate(lat, lon), 2, 3,
                Main(), "Name1", Rain(), Sys(), 4, 5, listOf(Weather()), Wind()
            )
        )
        val expectedWeatherList = listOf(
            Weather(
                "Base1", Clouds(), 1, Coordinate(lat, lon), 2, 3, Main(),
                "Name1", Rain(), Sys(), 4, 5, listOf(WeatherItem()), Wind()
            )
        )

        whenever(dao.getWeatherForLocationList()).thenReturn(flowOf(weatherList))
        val result = dataSource.getWeatherForLocationList().first()

        assertEquals(expectedWeatherList, result)
    }

    @Test
    fun testAddWeatherList() = runTest {
        val lat = 100.0
        val lon = 100.0
        val localWeatherList = listOf(
            WeatherEntity(
                null, "Base1", Clouds(), 1, Coordinate(lat, lon), 2, 3,
                Main(), "Name1", Rain(), Sys(), 4, 5, listOf(Weather()), Wind()
            )
        )
        val weatherList = listOf(
            Weather(
                "Base1", Clouds(), 1, Coordinate(lat, lon), 2, 3, Main(),
                "Name1", Rain(), Sys(), 4, 5, listOf(WeatherItem()), Wind()
            )
        )

        dataSource.addWeatherForLocation(weatherList)

        verify(dao).insertWeatherList(localWeatherList)
    }
}