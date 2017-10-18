package com.example.android.miniweather.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {

    final String HTTP_PREFIX = "http:";

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
        return HTTP_PREFIX + icon;
    }

}