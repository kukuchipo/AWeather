package com.ktctek.aweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DailyForecastViewHolder(
    view: View,
    private val tempDisplaySettingsManager: TempDisplaySettingsManager
    ) : RecyclerView.ViewHolder(view){
    private val tempText = view.findViewById<TextView>(R.id.textViewTempText)
    private val descriptionText = view.findViewById<TextView>(R.id.textViewDescritionText)

    fun bind(dailyForcast: DailyForcast){
        tempText.text = formatTempForDisplay(dailyForcast.temperature, tempDisplaySettingsManager.getTempDisplaySetting()) //dailyForcast.temperature.toString()
        descriptionText.text = dailyForcast.description
    }
}
class DailyForecastAdapater(
    private val tempDisplaySettingsManager: TempDisplaySettingsManager,
    private val clickHandler: (DailyForcast)->Unit,
) : ListAdapter<DailyForcast, DailyForecastViewHolder>(DIFF_CONFIG) {

    companion object{
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForcast>(){
            override fun areItemsTheSame(oldItem: DailyForcast, newItem: DailyForcast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DailyForcast, newItem: DailyForcast): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(itemView,tempDisplaySettingsManager)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
    }
}