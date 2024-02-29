package com.kryptopass.owmapi.ui.list

import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.WeatherItem
import com.kryptopass.domain.entity.weather.Wind

data class WeatherListModel(
    val items: List<WeatherItemModel> = listOf()
)

data class WeatherItemModel(
    val base: String? = null,
    val clouds: Clouds? = null,
    val cod: Int? = null,
    val coordinate: Coordinate? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val rain: Rain? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val visibility: Int? = null,
    val weatherItemList: List<WeatherItem?>? = null,
    val wind: Wind? = null
)