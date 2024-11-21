package com.example.skycastp2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skycastp2.adapter.ForecastAdapter
import com.example.skycastp2.data.ForecastData
import com.example.skycastp2.database.CityRepository
import com.example.skycastp2.database.CityViewModel
import com.example.skycastp2.database.DatabaseInstance
import com.example.skycastp2.databinding.ActivityForecastBinding
import com.example.skycastp2.viewModel.ForecastViewModel

class ForecastActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityForecastBinding
    private lateinit var forecastAdapter: ForecastAdapter
    private val forecastViewModel: ForecastViewModel by viewModels()
    private lateinit var cityViewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el Dao y Repository para Room
        val cityDao = DatabaseInstance.getDatabase(applicationContext).cityDao()
        val repository = CityRepository(cityDao)
        cityViewModel = CityViewModel(repository)

        // Observar la ciudad guardada
        cityViewModel.city.observe(this) { city ->
            val cityName = city?.name ?: "Ushuaia"

            forecastViewModel.fetchForecast(cityName)
        }

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