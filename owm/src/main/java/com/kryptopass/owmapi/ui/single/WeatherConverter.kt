package com.kryptopass.owmapi.ui.single

import android.content.Context
import com.kryptopass.common.state.CommonResultConverter
import com.kryptopass.domain.usecase.GetWeatherByCityAndCountryCodeUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeatherConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetWeatherByCityAndCountryCodeUseCase.Response, WeatherModel>() {

    override fun convertSuccess(data: GetWeatherByCityAndCountryCodeUseCase.Response): WeatherModel {
        return WeatherModel(
            data.weather?.base,
            data.weather?.clouds,
            data.weather?.cod,
            data.weather?.coordinate,
            data.weather?.dt,
            data.weather?.id,
            data.weather?.main,
            data.weather?.name,
            data.weather?.rain,
            data.weather?.sys,
            data.weather?.timezone,
            data.weather?.visibility,
            data.weather?.weatherItemList,
            data.weather?.wind
        )
    }
}