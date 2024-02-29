package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeatherListUseCase(
    configuration: Configuration,
    private val repo: WeatherRepository
) : UseCase<GetWeatherListUseCase.Request, GetWeatherListUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getWeatherList()
            .map {
                Response(it)
            }

    data object Request : UseCase.Request
    data class Response(val weatherList: List<Weather>) : UseCase.Response
}