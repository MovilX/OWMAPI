package com.kryptopass.owmapi.ui.single

import androidx.lifecycle.viewModelScope
import com.kryptopass.common.state.MviViewModel
import com.kryptopass.common.state.UiSingleEvent
import com.kryptopass.common.state.UiState
import com.kryptopass.domain.usecase.GetWeatherByCityAndCountryCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: GetWeatherByCityAndCountryCodeUseCase,
    private val converter: WeatherConverter
) : MviViewModel<WeatherModel, UiState<WeatherModel>, WeatherUiAction, UiSingleEvent>() {
    override fun initState(): UiState<WeatherModel> = UiState.Loading

    override fun handleAction(action: WeatherUiAction) {
        when (action) {
            is WeatherUiAction.Load -> {
                loadLaunch(action.cityAndCountryCode)
            }
        }
    }

    private fun loadLaunch(cityAndCountryCode: String) {
        viewModelScope.launch {
            useCase.execute(GetWeatherByCityAndCountryCodeUseCase.Request(cityAndCountryCode))
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }
}