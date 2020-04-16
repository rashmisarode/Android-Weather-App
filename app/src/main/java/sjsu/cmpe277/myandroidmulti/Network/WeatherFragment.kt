package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

import sjsu.cmpe277.myandroidmulti.R
import sjsu.cmpe277.myandroidmulti.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment() {

//    companion object {
//        fun newInstance() = WeatherFragment()
//    }

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<WeatherFragmentBinding>(inflater, R.layout.weather_fragment,container,false)
        //viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        /*viewModel._response.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.weathertextView.text = newresponse.toString() //display the raw json data
        })*/
        viewModel._name.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.cityTitle.text = newresponse.toString()
        })
        viewModel._datetoday.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.todayDateText.text = newresponse.toString()
        })
        viewModel._mainweather.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.mainWeatherText.text = newresponse.toString()
        })
        viewModel._maintemp.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.tempText.text = newresponse.toString()
        })
        viewModel._tempminmax.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.tempMinMaxText.text = newresponse.toString()
        })
        viewModel._humidity.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.humidityData.text = newresponse.toString()
        })
        viewModel._wind.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.windData.text = newresponse.toString()
        })
        viewModel._pressure.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.pressureData.text = newresponse.toString()
        })
        viewModel._sunrise.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.sunriseData.text = newresponse.toString()
        })
        viewModel._sunset.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.sunsetData.text = newresponse.toString()
        })
        return binding.root//inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
