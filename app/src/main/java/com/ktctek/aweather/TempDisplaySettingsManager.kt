package com.ktctek.aweather

import android.content.Context

enum class TempDisplaySetting{
    Fahrenheit, Celsius
}

class TempDisplaySettingsManager(context: Context){
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting){
        preferences.edit().putString("key_temp_display", setting.name).commit()
    }

    fun getTempDisplaySetting(): TempDisplaySetting{
        val settingValue = preferences.getString("key_temp_display", TempDisplaySetting.Celsius.name) ?: TempDisplaySetting.Celsius.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}