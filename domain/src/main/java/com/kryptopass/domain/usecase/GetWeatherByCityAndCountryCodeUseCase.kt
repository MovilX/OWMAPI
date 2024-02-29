package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeatherByCityAndCountryCodeUseCase(
    configuration: Configuration,
    private val repo: WeatherRepository
) : UseCase<GetWeatherByCityAndCountryCodeUseCase.Request, GetWeatherByCityAndCountryCodeUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getWeatherByCityAndCountryCode(request.cityAndCountryCode)
            .map {
                Response(it)
            }

    data class Request(val cityAndCountryCode: String) : UseCase.Request
    data class Response(val weather: Weather?) : UseCase.Response
}