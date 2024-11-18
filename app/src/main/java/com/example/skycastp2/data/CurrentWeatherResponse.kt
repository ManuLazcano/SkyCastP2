package com.example.skycastp2.data

data class CurrentWeatherResponse(
    val data: List<WeatherData>
)

data class WeatherData(
    val city_name: String,
    val temp: Double,
    val weather: WeatherInfo
)

data class WeatherInfo(
    val description: String,
    val icon: String
)
