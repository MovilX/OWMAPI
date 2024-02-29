package com.kryptopass.owmapi.ui.list

import com.kryptopass.common.state.UiSingleEvent

// NOTE: concrete UiSingleEvent specific for Launch
sealed class WeatherListUiSingleEvent : UiSingleEvent {

    data class OpenWeatherScreen(val navRoute: String) : WeatherListUiSingleEvent()
}