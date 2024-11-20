package com.example.skycastp2.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(private val repository: CityRepository) : ViewModel() {

    private val _city = MutableLiveData<City?>()
    val city: LiveData<City?> get() = _city

    fun insertCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCity(City(cityName))
        }
    }

    fun getCity() {
        viewModelScope.launch(Dispatchers.IO) {
            _city.postValue(repository.getCity())
        }
    }

    fun clearCities() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearCities()
        }
    }

}