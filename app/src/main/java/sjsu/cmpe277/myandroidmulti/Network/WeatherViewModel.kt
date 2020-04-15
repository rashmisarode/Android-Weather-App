package sjsu.cmpe277.myandroidmulti.Network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val WeatherAPPID = "2b492c001d57cd5499947bd3d3f9c47b"

class WeatherViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    // TODO: Implement the ViewModel
    // The internal MutableLiveData String that stores the most recent response
    val _response = MutableLiveData<String>()
    val _mainweather = MutableLiveData<String>()
    val _maintemp = MutableLiveData<String>()
    val _tempminmax = MutableLiveData<String>()
    val _humidity = MutableLiveData<String>()
    val _wind = MutableLiveData<String>()
    val _pressure = MutableLiveData<String>()



    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */
    init {
        getWeatherProperties()
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
                    Log.i("MainActivity", response.body()?.dt);

                    _response.value = "Success: ${response.body()?.name} city retrieved; Temperature: ${response.body()?.mainpart?.temp}; Humidity: ${response.body()?.mainpart?.humidity};" +
                            " Pressure: ${response.body()?.mainpart?.pressure}; Wind Speed: ${response.body()?.windpart?.speed};" +
                            "Main Weather: ${response.body()?.weatherPart?.get(0)?.main}"
                    _mainweather.value = "${response.body()?.weatherPart?.get(0)?.main} (${response.body()?.weatherPart?.get(0)?.description})"
                    _maintemp.value = "${response.body()?.mainpart?.temp}"+"°F"
                    _tempminmax.value = "Min: ${response.body()?.mainpart?.temp_min}"+"°F"+ " "+ "|"+ " " + "Max: ${response.body()?.mainpart?.temp_max}"+"°F"
                    _humidity.value = "${response.body()?.mainpart?.humidity}"+ " " + "%"
                    _wind.value = "${response.body()?.windpart?.speed}"+ " " + "mph"
                    _pressure.value = "${response.body()?.mainpart?.pressure}"+ " " + "atm"
                }

            }
        )
    }
}
