package com.example.skycastp2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    suspend fun insertCity(city: City)

    @Query("SELECT * FROM cities LIMIT 1")
    suspend fun getCity(): City?

    @Query("DELETE FROM cities")
    suspend fun clearCities()
}