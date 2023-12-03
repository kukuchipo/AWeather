package com.ktctek.aweather.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ktctek.aweather.R
import com.ktctek.aweather.TempDisplaySetting
import com.ktctek.aweather.TempDisplaySettingsManager
import com.ktctek.aweather.databinding.ActivityForecastDetailsBinding
import com.ktctek.aweather.formatTempForDisplay
import com.ktctek.aweather.showTempDisplaySettingDialog

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingsManager: TempDisplaySettingsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityForecastDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_forecast_details)

        tempDisplaySettingsManager = TempDisplaySettingsManager(this)

        setTitle(R.string.forecast_details)
        setContentView(binding.root)

        val temperatureText = binding.textViewTemperatureText
        val descriptionText = binding.textViewDescriptionText

        val temp = intent.getFloatExtra("key_temp", 0f)
        temperatureText.text = formatTempForDisplay( temp, tempDisplaySettingsManager.getTempDisplaySetting())
        descriptionText.text = intent.getStringExtra("key_description")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.tempDisplayItem ->{
                showTempDisplaySettingDialog(this, tempDisplaySettingsManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}