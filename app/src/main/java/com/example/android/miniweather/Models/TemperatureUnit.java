package com.example.android.miniweather.Models;

/**
 * Created by matev on 2017. 11. 30..
 */

public class TemperatureUnit {
    private boolean isCelsius;

    public TemperatureUnit() {
    }

    public boolean isCelsius() {
        return isCelsius;
    }

    public void setTemperatureUnitToCelsius(boolean celsius) {
        isCelsius = celsius;
    }
}
