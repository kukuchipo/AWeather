package com.ktctek.aweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForcastRepository {
    private val _weeklyForcast = MutableLiveData<List<DailyForcast>>()
    val weeklyForcast: LiveData<List<DailyForcast>> = _weeklyForcast

    fun loadForcast(zipcode: String){
        val randomValues = List(7){
            Random.nextFloat().rem(100)*100
        }
        val forecastItem = randomValues.map {temp ->
            DailyForcast(temp, getTempDesscription(temp))
        }
        _weeklyForcast.setValue(forecastItem)
    }

    private fun getTempDesscription(temp: Float): String{
        return when(temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0, doesn't make sense"
            in 0f.rangeTo(32f) -> "Way too cold."
            in 32f.rangeTo(55f) -> "Colder than I would prefer."
            in 55f.rangeTo(65f) -> "Not too bad."
            in 65f.rangeTo(80f) -> "That's the sweetspot!"
            in 80f.rangeTo(90f) -> "Getting too warm!"
            in 90f.rangeTo(100f) -> "Where's the AC?"
            in 100f.rangeTo(Float.MAX_VALUE) -> "What is this, Desert Arizona?"
            else -> "Does not compute"
        }
    }
}