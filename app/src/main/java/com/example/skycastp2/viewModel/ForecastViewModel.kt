package com.example.skycastp2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycastp2.data.ForecastData
import com.example.skycastp2.data.RetrofitClient
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val _forecast = MutableLiveData<List<ForecastData>>()
    val forecast: LiveData<List<ForecastData>> get() = _forecast

    fun fetchForecast(city: String) {
        viewModelScope.launch {
            val response = RetrofitClient.api.getForecast(city)
            if (response.isSuccessful) {
                _forecast.value = response.body()?.data ?: emptyList()
            }
        }
    }
}