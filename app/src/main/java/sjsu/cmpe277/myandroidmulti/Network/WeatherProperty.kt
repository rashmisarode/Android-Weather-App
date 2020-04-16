package sjsu.cmpe277.myandroidmulti.Network

import androidx.activity.OnBackPressedCallback
import com.squareup.moshi.Json
import java.util.*
import kotlin.collections.ArrayList

data class WeatherProperty (
    val id: String,
    // used to map main from the JSON to mainpart in our class
    @Json(name = "main") val mainpart: MainWeatherPart,
    @Json(name = "wind") val windpart: WindWeatherPart,
    @Json(name = "weather") val weatherPart: List<WeatherPart>,
    @Json(name = "sys") val sysPart: SysWeatherPart,
    val name: String,
    val cod: Double,
    val dt: Long
)

data class MainWeatherPart(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double
)
data class WindWeatherPart(
    val speed: Double,
    val deg: Double
)
data class WeatherPart(
    val main: String,
    val description: String
)
data class SysWeatherPart(
    val sunrise: Long,
    val sunset: Long
)