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

class GetWeatherByCityAndCountryCodeUseCaseTest {

    private val repo = mock<WeatherRepository>()
    private val useCase = GetWeatherByCityAndCountryCodeUseCase(
        mock(),
        repo
    )

    @Test
    fun testProcess() = runTest {
        val request = GetWeatherByCityAndCountryCodeUseCase.Request("City1")
        val weather = Weather(
            "Base2", Clouds(), 6, Coordinate(), 7, 8, Main(), "Name1",
            Rain(), Sys(), 9, 10, listOf(), Wind()
        )

        whenever(repo.getWeatherByCityAndCountryCode(request.cityAndCountryCode)).thenReturn(flowOf(weather))
        val response = useCase.process(request).first()

        assertEquals(GetWeatherByCityAndCountryCodeUseCase.Response(weather), response)
    }
}