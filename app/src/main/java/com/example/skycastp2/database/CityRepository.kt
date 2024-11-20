package com.example.skycastp2.database

class CityRepository(private val cityDao: CityDao) {

    suspend fun insertCity(city: City) = cityDao.insertCity(city)
    suspend fun getCity(): City? = cityDao.getCity()
    suspend fun clearCities() = cityDao.clearCities()
}