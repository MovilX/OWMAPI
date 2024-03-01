package com.kryptopass.owmapi.ui.single

import com.kryptopass.common.state.UiAction

sealed class WeatherUiAction : UiAction {

    data class Load(val cityAndCountryCode: String) : WeatherUiAction()
}