package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeatherForLocationListUseCase(
    configuration: Configuration,
    private val repo: WeatherRepository
) : UseCase<GetWeatherForLocationListUseCase.Request, GetWeatherForLocationListUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getWeatherForLocationList()
            .map {
                Response(it)
            }

    data object Request : UseCase.Request
    data class Response(val locationsWeatherList: List<Weather>) : UseCase.Response
}