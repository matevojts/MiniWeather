package hu.matevojts.android.miniweather.list.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityWeather(
    @SerializedName("location") @Expose val location: Location,
    @SerializedName("forecast") @Expose val forecast: Forecast
)

data class Location(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("country") @Expose val country: String
)

data class Forecast(@SerializedName("forecastday") @Expose val forecastday: List<Forecastday>)

data class Forecastday(
    @SerializedName("date") @Expose val date: String,
    @SerializedName("day") @Expose val day: Day,
    @SerializedName("astro") @Expose val astro: Astro
)

data class Day(
    @SerializedName("maxtemp_c") @Expose val maxtempC: Double,
    @SerializedName("maxtemp_f") @Expose val maxtempF: Double,
    @SerializedName("mintemp_c") @Expose val mintempC: Double,
    @SerializedName("mintemp_f") @Expose val mintempF: Double,
    @SerializedName("condition") @Expose val condition: Condition
)

data class Astro(
    @SerializedName("sunrise") @Expose val sunrise: String,
    @SerializedName("sunset") @Expose val sunset: String
)

data class Condition(@SerializedName("icon") @Expose val icon: String) {
    val iconURL: String
        get() = "http:$icon"
}
