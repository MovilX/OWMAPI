package com.kryptopass.owmapi.ui.list

import android.content.Context
import com.kryptopass.common.state.CommonResultConverter
import com.kryptopass.domain.usecase.GetWeatherForLocationListUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeatherListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetWeatherForLocationListUseCase.Response, WeatherListModel>() {

    override fun convertSuccess(
        data: GetWeatherForLocationListUseCase.Response
    ): WeatherListModel {
        return WeatherListModel(
            items = data.locationsWeatherList.map {
                WeatherItemModel(
                    it.base,
                    it.clouds,
                    it.cod,
                    it.coordinate,
                    it.dt,
                    it.id,
                    it.main,
                    it.name,
                    it.rain,
                    it.sys,
                    it.timezone,
                    it.visibility,
                    it.weatherItemList,
                    it.wind
                )
            }
        )
    }
}