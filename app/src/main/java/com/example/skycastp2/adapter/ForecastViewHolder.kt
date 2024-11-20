package com.example.skycastp2.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.skycastp2.data.ForecastData
import com.example.skycastp2.databinding.ItemForecastBinding

class ForecastViewHolder(private val binding: ItemForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: ForecastData) {
        binding.tvDate.text = forecast.valid_date
        binding.tvMaxTemp.text = "Max: ${forecast.max_temp}°C"
        binding.tvMinTemp.text = "Min: ${forecast.min_temp}°C"
        binding.tvDescription.text = forecast.weather.description
    }
}