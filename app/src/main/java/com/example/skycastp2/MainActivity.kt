package com.example.skycastp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.skycastp2.data.CurrentWeatherResponse
import com.example.skycastp2.database.CityRepository
import com.example.skycastp2.database.CityViewModel
import com.example.skycastp2.database.DatabaseInstance
import com.example.skycastp2.databinding.ActivityMainBinding
import com.example.skycastp2.databinding.DialogLocationBinding
import com.example.skycastp2.fragment.ContactFormFragment
import com.example.skycastp2.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var cityViewModel: CityViewModel
    private lateinit var dialogLocationBinding: DialogLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogLocationBinding = DialogLocationBinding.inflate((layoutInflater))
        setContentView(binding.root)

        // Configuración del AlertDialog
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogLocationBinding.root)
        val alertDialog = alertDialogBuilder.create()

        setListener(alertDialog)


        // Obtener el Dao y Repository para Room
        val cityDao = DatabaseInstance.getDatabase(applicationContext).cityDao()
        val repository = CityRepository(cityDao)
        cityViewModel = CityViewModel(repository)

        // Obtener la ciudad guardada en la base de datos
        cityViewModel.getCity()

        // Observar los cambios de la ciudad
        cityViewModel.city.observe(this) { city ->
            val cityName = city?.name ?: "Ushuaia"

            weatherViewModel.fetchCurrentWeather(cityName)
        }


        weatherViewModel.currentWeather.observe(this) { weatherResponse ->
            weatherResponse?.let {
                updateUI(it)
            }
        }

        binding.btnForecast.setOnClickListener {
            startActivity(Intent(this, ForecastActivity::class.java))
        }

        binding.btnOpenContactForm.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ContactFormFragment())
                .addToBackStack(null) // Permite volver atrás
                .commit()
        }

    }

    private fun updateUI(weather: CurrentWeatherResponse) {
        binding.tvCity.text = weather.data[0].city_name
        binding.tvTemp.text = "${weather.data[0].temp}°C"
        binding.tvDescription.text = weather.data[0].weather.description
    }

    private fun setListener(alertDialog: AlertDialog) {
         binding.btnAlertDialog.setOnClickListener {
            showAlertDialog(alertDialog)
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        alertDialog.show()
        clearEditText()

        dialogLocationBinding.btnSaveLocation.setOnClickListener {
            val city = addCity()
            if (city.isNotEmpty()) {
                cityViewModel.insertCity(city)
                weatherViewModel.fetchCurrentWeather(city)

                alertDialog.dismiss()
            }
        }
    }

    private fun addCity(): String {
        return dialogLocationBinding.editCityName.text.toString()
    }

    private fun clearEditText() {
        dialogLocationBinding.editCityName.text.clear()
    }

}