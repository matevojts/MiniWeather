package com.example.android.miniweather.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    private final String CELSIUS_SIGN = " \u2103";
    private final String FAHRENHEIT_SIGN = " \u2109";

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxtempC;
    @SerializedName("maxtemp_f")
    @Expose
    private Double maxtempF;
    @SerializedName("mintemp_c")
    @Expose
    private Double mintempC;
    @SerializedName("mintemp_f")
    @Expose
    private Double mintempF;
    @SerializedName("condition")
    @Expose
    private Condition condition;

    public Double getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(Double maxtempC) {
        this.maxtempC = maxtempC;
    }

    public Double getMintempC() {
        return mintempC;
    }

    public void setMintempC(Double mintempC) {
        this.mintempC = mintempC;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(Double maxtempF) {
        this.maxtempF = maxtempF;
    }

    public Double getMintempF() {
        return mintempF;
    }

    public void setMintempF(Double mintempF) {
        this.mintempF = mintempF;
    }

    public String getMintempCelsiusText() {
        return Double.toString(mintempC) + CELSIUS_SIGN;
    }

    public String getMaxtempCelsiusText() {
        return Double.toString(maxtempC) + CELSIUS_SIGN;
    }

    public String getMintempFahrenheitText() {
        return Double.toString(mintempF) + FAHRENHEIT_SIGN;
    }

    public String getMaxtempFahrenheitText() {
        return Double.toString(maxtempF) + FAHRENHEIT_SIGN;
    }

}