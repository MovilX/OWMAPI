package com.kryptopass.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kryptopass.domain.entity.weather.Clouds
import com.kryptopass.domain.entity.weather.Coordinate
import com.kryptopass.domain.entity.weather.Main
import com.kryptopass.domain.entity.weather.Rain
import com.kryptopass.domain.entity.weather.Sys
import com.kryptopass.domain.entity.weather.Weather
import com.kryptopass.domain.entity.weather.Wind

class WeatherConverters {

    @TypeConverter
    fun listToJsonString(value: List<Weather?>?): String? = Gson().toJson(value)
    @TypeConverter
    fun jsonStringToList(value: String?) =
        Gson().fromJson(value, Array<Weather?>::class.java).toList()

    @TypeConverter
    fun cloudsToString(value: Clouds?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
    @TypeConverter
    fun stringToClouds(clouds: String?) = Gson().fromJson(clouds, Clouds::class.java)


    @TypeConverter
    fun coordinateToString(value: Coordinate?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
    @TypeConverter
    fun stringToCoordinate(coordinate: String?) = Gson().fromJson(coordinate, Coordinate::class.java)

    @TypeConverter
    fun mainToString(value: Main?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
    @TypeConverter
    fun stringToMain(main: String?) = Gson().fromJson(main, Main::class.java)

    @TypeConverter
    fun rainToString(value: Rain?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
    @TypeConverter
    fun stringToRain(rain: String?) = Gson().fromJson(rain, Rain::class.java)

    @TypeConverter
    fun sysToString(value: Sys?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
    @TypeConverter
    fun stringToSys(sys: String?) = Gson().fromJson(sys, Sys::class.java)

    @TypeConverter
    fun windToString(value: Wind?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
    @TypeConverter
    fun stringToWind(sys: String?) = Gson().fromJson(sys, Wind::class.java)
}