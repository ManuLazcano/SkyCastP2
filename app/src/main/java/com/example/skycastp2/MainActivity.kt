package com.example.skycastp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        binding.btnForecast.setOnClickListener {
            startActivity(Intent(this, ForecastActivity::class.java))
        }

    }

    private fun updateUI(weather: CurrentWeatherResponse) {
        binding.tvCity.text = weather.data[0].city_name
        binding.tvTemp.text = "${weather.data[0].temp}°C"
        binding.tvDescription.text = weather.data[0].weather.description
    }
}