package com.example.android.miniweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.android.miniweather.ui.WeatherViewFragment

class MainActivity : AppCompatActivity() {

    private val weatherViewFragment = WeatherViewFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.moveToFirstScreen(supportFragmentManager, weatherViewFragment)
    }

}