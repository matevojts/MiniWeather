package com.example.android.miniweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.android.miniweather.Manager.NavigationManager
import com.example.android.miniweather.View.WeatherViewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayFragment()
    }

    private fun displayFragment() {
        NavigationManager.moveToFirstScreen(fragmentManager, WeatherViewFragment())
    }
}