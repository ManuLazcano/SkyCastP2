package com.example.skycastp2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycastp2.data.CurrentWeatherResponse
import com.example.skycastp2.data.RetrofitClient
import com.example.skycastp2.data.WeatherApiService
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherResponse>()
    val currentWeather: LiveData<CurrentWeatherResponse> get() = _currentWeather

    fun fetchCurrentWeather(city: String) {
        viewModelScope.launch {
            val response = RetrofitClient.api.getCurrentWeather(city)

            if (response.isSuccessful) {
                _currentWeather.value = response.body()
            }
        }
    }
}