package com.ktctek.aweather

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String{
    return when (tempDisplaySetting){
        TempDisplaySetting.Fahrenheit -> String.format("%.2f °F", temp)
        TempDisplaySetting.Celsius -> {
            val temp = (temp - 32f) * (5f/9f)
            return String.format("%.2f °C", temp)
        }
    }
}

fun showTempDisplaySettingDialog(context: Context, tempDisplaySettingsManager: TempDisplaySettingsManager){
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Choose display unit")
        .setMessage("Choose which temperature unit to use for temperature display")
        .setPositiveButton("°F"){_,_ ->
            tempDisplaySettingsManager.updateSetting(TempDisplaySetting.Fahrenheit)
        }
        .setNeutralButton("°C"){_,_ ->
            tempDisplaySettingsManager.updateSetting(TempDisplaySetting.Celsius)
        }
        .setOnDismissListener {
            Toast.makeText(context, "Settings will take effect one next app launch", Toast.LENGTH_SHORT).show()
        }

    dialogBuilder.show()
}