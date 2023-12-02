    package com.ktctek.aweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
//import android.widget.EditText
import com.ktctek.aweather.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() {
        private val forecastRepository = ForcastRepository()
        // region Setup Methods
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_main)
            setContentView(binding.root)
            // val zipcodeEntry: EditText = findViewById(R.id.editTextZipcode)
            val zipcodeEntry = binding.editTextZipcode
            val submitZip = binding.buttonSubmitZip
            val forecastList = binding.forcastList
            forecastList.layoutManager = LinearLayoutManager(this)
            val dailyForecastAdapater = DailyForecastAdapater(){forecastItem ->
                val msg = getString(R.string.forecast_clicked_format, forecastItem.temperature, forecastItem.description)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
}