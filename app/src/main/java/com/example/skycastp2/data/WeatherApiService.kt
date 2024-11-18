package com.example.skycastp2.data

import com.example.skycastp2.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = "es"
    ): Response<CurrentWeatherResponse>

    @GET("forecast/daily")
    suspend fun getForecast(
        @Query("city") city: String,
        @Query("days") days: Int = 5,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = "es"
    ): Response<ForecastResponse>
}