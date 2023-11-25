    package com.ktctek.aweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
//import android.widget.EditText
import com.ktctek.aweather.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() {
    // region Setup Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        // val zipcodeEntry: EditText = findViewById(R.id.editTextZipcode)
        val zipcodeEntry = binding.editTextZipcode
        val submitZip = binding.buttonSubmitZip

        submitZip.setOnClickListener{
            val zipcode = zipcodeEntry.text.toString()
            if(zipcode.length >= 3 ){
                Toast.makeText(this, zipcode, Toast.LENGTH_LONG ).show()
            }else{
                Toast.makeText(this, R.string.zip_error, Toast.LENGTH_LONG ).show()
            }
        }
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }
    // endregion Setup Methods

    // region Teardown Methods
    override fun onStop() {
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
    // endregion Teardown Methods
}