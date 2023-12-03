    package com.ktctek.aweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
//import android.widget.EditText
import com.ktctek.aweather.databinding.ActivityMainBinding
import com.ktctek.aweather.details.ForecastDetailsActivity

    class MainActivity : AppCompatActivity() {
        private lateinit var tempDisplaySettingsManager: TempDisplaySettingsManager
        private val forecastRepository = ForcastRepository()
        // region Setup Methods
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_main)

            tempDisplaySettingsManager = TempDisplaySettingsManager(this)

            setTitle(R.string.app_name)
            setContentView(binding.root)
            // val zipcodeEntry: EditText = findViewById(R.id.editTextZipcode)
            val zipcodeEntry = binding.editTextZipcode
            val submitZip = binding.buttonSubmitZip
            val forecastList = binding.forcastList
            forecastList.layoutManager = LinearLayoutManager(this)
            val dailyForecastAdapater = DailyForecastAdapater(tempDisplaySettingsManager){ forecastItem ->
                val msg = getString(R.string.forecast_clicked_format, forecastItem.temperature, forecastItem.description)
                showForecastDetails(forecastItem)
            }
            forecastList.adapter = dailyForecastAdapater
            val weeklyForecastObserver = Observer<List<DailyForcast>>{ forecastItems ->
                // update our list adapter
                //Toast.makeText(this, "Loaded items", Toast.LENGTH_LONG).show()
                dailyForecastAdapater.submitList(forecastItems)
            }
            forecastRepository.weeklyForcast.observe(this, weeklyForecastObserver)


            submitZip.setOnClickListener {
                val zipcode = zipcodeEntry.text.toString()
                if (zipcode.length >= 3) {
                    // Toast.makeText(this, zipcode, Toast.LENGTH_LONG).show()
                    forecastRepository.loadForcast(zipcode)
                } else {
                    Toast.makeText(this, R.string.zip_error, Toast.LENGTH_LONG).show()
                }
            }
        }
        // endregion Setup Methods
        private fun showForecastDetails(forecast: DailyForcast){
            //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            val forcastDetailsIntent = Intent(this, ForecastDetailsActivity::class.java)
            forcastDetailsIntent.putExtra("key_temp", forecast.temperature)
            forcastDetailsIntent.putExtra("key_description", forecast.description)
            startActivity(forcastDetailsIntent)
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
