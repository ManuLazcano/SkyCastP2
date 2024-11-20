package com.example.skycastp2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skycastp2.adapter.ForecastAdapter
import com.example.skycastp2.data.ForecastData
import com.example.skycastp2.databinding.ActivityForecastBinding
import com.example.skycastp2.viewModel.ForecastViewModel

class ForecastActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityForecastBinding
    private lateinit var forecastAdapter: ForecastAdapter
    private val forecastViewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Llamada para cargar el pronóstico
        forecastViewModel.fetchForecast("Buenos Aires")

        // Observador de los datos
        forecastViewModel.forecast.observe(this) { forecastResponse ->
            forecastResponse?.let {
                initRecycler(it)
            }
        }
    }

    fun initRecycler(forecastList: List<ForecastData>) {
        binding.rvForecast.layoutManager = LinearLayoutManager(this)
        forecastAdapter = ForecastAdapter(forecastList)
        binding.rvForecast.adapter = forecastAdapter
    }
}