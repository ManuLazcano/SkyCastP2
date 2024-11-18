package com.example.skycastp2.data

data class ForecastResponse(
    val data: List<ForecastData>
)

data class ForecastData(
    val valid_date: String,
    val temp: Double,
    val max_temp: Double,
    val min_temp: Double,
    val weather: WeatherInfo
)
