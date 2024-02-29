package com.kryptopass.domain.entity.weather

data class Weather(
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