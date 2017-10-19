package com.example.android.miniweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.miniweather.View.WeatherViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayFragment();
    }

    private void displayFragment(){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WeatherViewFragment weatherViewFragment = new WeatherViewFragment();
        fragmentTransaction.add(R.id.weather_view_fragment, weatherViewFragment);
        fragmentTransaction.commit();
    }
}