package com.example.mykotlinpogoda.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mykotlinpogoda.databinding.DetailFragmentBinding
import com.example.mykotlinpogoda.model.City
import com.example.mykotlinpogoda.model.Weather
import com.example.mykotlinpogoda.model.WeatherDTO
import com.example.mykotlinpogoda.model.WeatherLoder

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment

        }

    }


    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!


//    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Weather>("WEATHER_EXTRA")?.let { weather ->

            binding.cityName.text = weather.city.name
            binding.cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
            WeatherLoder.load(weather.city, object : WeatherLoder.OnWeatherLoadListner {
                override fun onLoded(weatherDTO: WeatherDTO) {

                    weatherDTO.factDTO.let {

                        binding.weatherCondition.text = weatherDTO.factDTO.condition
                        binding.temperature.text= weatherDTO.factDTO.temp.toString()
                        binding.feelsLikeValue.text = weatherDTO.factDTO.feels_like.toString()
                    }

                }

                override fun onFaiLed(throwable: Throwable) {
                  Toast.makeText(requireContext(),throwable.message,Toast.LENGTH_LONG).show()
                }
            })//1.52.07

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
