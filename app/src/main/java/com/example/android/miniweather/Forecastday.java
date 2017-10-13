package com.example.android.miniweather;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecastday {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day")
    @Expose
    private Day day;
    @SerializedName("astro")
    @Expose
    private Astro astro;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

}