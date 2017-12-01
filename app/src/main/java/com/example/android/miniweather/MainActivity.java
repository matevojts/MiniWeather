package com.example.android.miniweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.miniweather.Manager.NavigationManager;
import com.example.android.miniweather.View.WeatherViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFragment();
    }

    private void displayFragment(){
        NavigationManager.moveToScreen(getFragmentManager(), new WeatherViewFragment());
    }
}