package com.kryptopass.owmapi.ui.list

import com.kryptopass.common.state.UiAction

// NOTE: concrete UiAction specific for Launch
sealed class WeatherListUiAction : UiAction {

    data object Load : WeatherListUiAction()
    data class OnItemClick(val cityAndCountryCode: String) : WeatherListUiAction()
}