package com.example.android.miniweather.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxtempC;
    @SerializedName("mintemp_c")
    @Expose
    private Double mintempC;
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

    public String getMintempString(){
        return Double.toString(mintempC) + " \u2103";
    }

    public String getMaxtempString(){
        return Double.toString(maxtempC) + " \u2103";
    }

}