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

      /*  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String defaultUnit = sharedPreferences.getString(getResources().getString(R.string.settings_temperature_unit_key), "celsius");
        Toast.makeText(this, defaultUnit, Toast.LENGTH_LONG).show();*/

        displayFragment();
    }

    private void displayFragment(){
        NavigationManager.moveToScreen(getFragmentManager(), new WeatherViewFragment());
    }
}