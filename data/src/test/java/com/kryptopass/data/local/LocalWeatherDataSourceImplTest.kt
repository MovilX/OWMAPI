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

    private val city = "Atlanta"

    @Test
    fun testGetWeatherList() = runTest {
        val weatherList = listOf(
            WeatherEntity(
                city, "Base1", Clouds(), 1, Coordinate(), 2, 3,
                Main(), Rain(), Sys(), 4, 5, listOf(), Wind()
            )
        )
        val expectedWeatherList = listOf(
            Weather(
                "Base1", Clouds(), 1, Coordinate(), 2, 3, Main(),
                city, Rain(), Sys(), 4, 5, listOf(WeatherItem()), Wind()
            )
        )

        whenever(dao.getWeatherForLocations()).thenReturn(flowOf(weatherList))
        val result = dataSource.getWeatherList().first()

        assertEquals(expectedWeatherList, result)
    }

    @Test
    fun testAddWeatherList() = runTest {
        val localWeatherList = listOf(
            WeatherEntity(
                city, "Base1", Clouds(), 1, Coordinate(), 2, 3,
                Main(), Rain(), Sys(), 4, 5, listOf(), Wind()
            )
        )
        val weatherList = listOf(
            Weather(
                "Base1", Clouds(), 1, Coordinate(), 2, 3, Main(),
                city, Rain(), Sys(), 4, 5, listOf(WeatherItem()), Wind()
            )
        )

        dataSource.addWeatherForCityAndCountryCode(weatherList)

        verify(dao).insertWeatherList(localWeatherList)
    }
}