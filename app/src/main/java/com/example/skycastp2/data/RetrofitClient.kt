package com.example.skycastp2.data

import com.example.skycastp2.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}