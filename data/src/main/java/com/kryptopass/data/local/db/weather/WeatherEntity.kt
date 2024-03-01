package com.kryptopass.data.local.db.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.Wind

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "base") val base: String? = null,
    @ColumnInfo(name = "clouds") val clouds: Clouds? = null,
    @ColumnInfo(name = "cod") val cod: Int? = null,
    @ColumnInfo(name = "coordinate") val coordinate: Coordinate? = null,
    @ColumnInfo(name = "dt") val dt: Int? = null,
    @ColumnInfo(name = "weatherId") val weatherId: Int? = null,
    @ColumnInfo(name = "main") val main: Main? = null,
    @ColumnInfo(name = "rain") val rain: Rain? = null,
    @ColumnInfo(name = "sys") val sys: Sys? = null,
    @ColumnInfo(name = "timezone") val timezone: Int? = null,
    @ColumnInfo(name = "visibility") val visibility: Int? = null,
    @ColumnInfo(name = "weather") val weather: List<Weather?>? = null,
    @ColumnInfo(name = "wind") val wind: Wind? = null
)
