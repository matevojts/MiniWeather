package com.example.android.miniweather.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("icon")
    @Expose
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconURL() {
        return "http:" + icon;
    }

}