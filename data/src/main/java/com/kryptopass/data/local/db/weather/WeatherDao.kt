package com.kryptopass.data.local.db.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kryptopass.domain.entity.weather.Coordinate
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeatherForLocationList(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM weather WHERE coordinate = :coordinate")
    fun getWeatherByLocation(coordinate: Coordinate?): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherList(launch: List<WeatherEntity>)
}