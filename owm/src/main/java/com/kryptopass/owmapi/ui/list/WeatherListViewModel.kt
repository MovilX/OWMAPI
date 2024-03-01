package com.kryptopass.owmapi.ui.list

import androidx.lifecycle.viewModelScope
import com.kryptopass.common.nav.LocationInput
import com.kryptopass.common.nav.NavRoutes
import com.kryptopass.common.state.MviViewModel
import com.kryptopass.common.state.UiState
import com.kryptopass.domain.usecase.GetWeatherListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val useCase: GetWeatherListUseCase,
    private val converter: WeatherListConverter
) : MviViewModel<WeatherListModel, UiState<WeatherListModel>, WeatherListUiAction, WeatherListUiSingleEvent>() {

    override fun initState(): UiState<WeatherListModel> = UiState.Loading

    override fun handleAction(action: WeatherListUiAction) {
        when (action) {
            is WeatherListUiAction.Load -> {
                loadLaunches()
            }

            is WeatherListUiAction.OnItemClick -> {
                submitSingleEvent(
                    WeatherListUiSingleEvent.OpenWeatherScreen(
                        NavRoutes.Location.routeForLocation(
                            LocationInput(action.cityAndCountryCode)
                        )
                    )
                )
            }
        }
    }

    private fun loadLaunches() {
        viewModelScope.launch {
            useCase.execute(GetWeatherListUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }
}