package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.Wind
import com.kryptopass.domain.repo.WeatherRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetWeatherForLocationListUseCaseTest {

    private val repository = mock<WeatherRepository>()

    private val useCase = GetWeatherForLocationListUseCase(
        mock(),
        repository
    )

    @Test
    fun testProcess() = runTest {
        val weather1 = Weather(
            "Base1", Clouds(), 1, Coordinate(), 2, 3, Main(), "Name1",
            Rain(), Sys(), 4, 5, listOf(), Wind()
        )
        val weather2 = Weather(
            "Base2", Clouds(), 6, Coordinate(), 7, 8, Main(), "Name1",
            Rain(), Sys(), 9, 10, listOf(), Wind()
        )

        whenever(repository.getWeatherForLocationList()).thenReturn(flowOf(listOf(weather1, weather2)))
        val response = useCase.process(GetWeatherForLocationListUseCase.Request).first()

        assertEquals(GetWeatherForLocationListUseCase.Response(listOf(weather1, weather2)), response)
    }
}