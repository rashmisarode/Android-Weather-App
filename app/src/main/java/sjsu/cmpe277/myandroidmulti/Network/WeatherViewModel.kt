package sjsu.cmpe277.myandroidmulti.Network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.DateFormat
import java.time.Instant
import java.util.*
import kotlin.math.absoluteValue

private const val WeatherAPPID = "2b492c001d57cd5499947bd3d3f9c47b"

class WeatherViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    // TODO: Implement the ViewModel
    // The internal MutableLiveData String that stores the most recent response
    val _response = MutableLiveData<String>()
    val _name = MutableLiveData<String>()
    val _datetoday = MutableLiveData<String>()
    val _mainweather = MutableLiveData<String>()
    val _maintemp = MutableLiveData<String>()
    val _tempminmax = MutableLiveData<String>()
    val _humidity = MutableLiveData<String>()
    val _wind = MutableLiveData<String>()
    val _pressure = MutableLiveData<String>()
    val _sunrise = MutableLiveData<String>()
    val _sunset = MutableLiveData<String>()


    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */
    init {
        getWeatherProperties()
    }

    private fun getDate(l:Long?): Date {
        try {
            l?.let {
                Log.i("getDate", it.toString())
                return Date.from(Instant.ofEpochSecond(it))
            }
        } catch (e:Exception) {

        }
        return Date();
    }

    private fun getWeatherProperties() {
        //_response.value = "Set the Weather API Response here!"
        //WeatherApi.retrofitService.getProperties()
        WeatherApi.retrofitService.getProperties("San Jose", WeatherAPPID).enqueue(
            object: Callback<WeatherProperty> {
                override fun onFailure(call: Call<WeatherProperty>, t: Throwable) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<WeatherProperty>, response: Response<WeatherProperty>) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    //_response.value = response.body()

                   // Log.i("MainActivity", response.body()?.weatherPart?.get(0)?.description);

                    val mainTemp = (response.body()?.mainpart?.temp?.minus(273.15));
                    val maxTemp = (response.body()?.mainpart?.temp_max?.minus(273.15));
                    val minTemp = (response.body()?.mainpart?.temp_min?.minus(273.15));

                    val date  = getDate(response.body()?.dt);
                    val todayDt = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                    Log.i("MainActivity", DateFormat.getTimeInstance().format(date));
                    Log.i("MainActivity", DateFormat.getDateInstance(DateFormat.FULL).format(date));

                    val sunriseTemp = getDate(response.body()?.sysPart?.sunrise);
                    val sunriseTime = DateFormat.getTimeInstance().format(sunriseTemp);
                    val sunsetTemp = getDate(response.body()?.sysPart?.sunset);
                    val sunsetTime = DateFormat.getTimeInstance().format(sunsetTemp);

                    //Log.i("MainActivity", response.body()?.dt.toString());

                    _response.value = "Success: ${response.body()?.name} city retrieved; Temperature: ${response.body()?.mainpart?.temp}; Humidity: ${response.body()?.mainpart?.humidity};" +
                            " Pressure: ${response.body()?.mainpart?.pressure}; Wind Speed: ${response.body()?.windpart?.speed};" +
                            "Main Weather: ${response.body()?.weatherPart?.get(0)?.main}"
                    _name.value = "${response.body()?.name}"
                    _datetoday.value = todayDt
                    _mainweather.value = "${response.body()?.weatherPart?.get(0)?.main} (${response.body()?.weatherPart?.get(0)?.description})"
                    _maintemp.value = String.format("%.1f", mainTemp) + "°C"
                    _tempminmax.value = "Min: " + String.format("%.1f", minTemp) +"°C"+ " "+ "|"+ " " + "Max: " + String.format("%.1f", maxTemp) +"°C"
                    _humidity.value = "${response.body()?.mainpart?.humidity}"+ " " + "%"
                    _wind.value = "${response.body()?.windpart?.speed}"+ " " + "mph"
                    _pressure.value = "${response.body()?.mainpart?.pressure}"+ " " + "atm"
                    _sunrise.value = sunriseTime
                    _sunset.value = sunsetTime
                }

            }
        )
    }
}
