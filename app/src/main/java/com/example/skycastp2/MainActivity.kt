package com.example.skycastp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.skycastp2.data.CurrentWeatherResponse
import com.example.skycastp2.databinding.ActivityMainBinding
import com.example.skycastp2.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Llamar a la API para obtener el clima actual
        weatherViewModel.fetchCurrentWeather("Buenos Aires")

        weatherViewModel.currentWeather.observe(this) { weatherResponse ->
            weatherResponse?.let {
                updateUI(it)
            }
        }
    }

    private fun updateUI(weather: CurrentWeatherResponse) {
        binding.tvCity.text = weather.data[0].city_name
        binding.tvTemp.text = "${weather.data[0].temp}°C"
        binding.tvDescription.text = weather.data[0].weather.description
    }
}