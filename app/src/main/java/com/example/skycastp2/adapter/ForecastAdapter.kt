package com.example.skycastp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skycastp2.data.ForecastData
import com.example.skycastp2.databinding.ItemForecastBinding

class ForecastAdapter(private var forecastList: List<ForecastData>) :
    RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.bind(forecast)
    }

    override fun getItemCount() = forecastList.size

}