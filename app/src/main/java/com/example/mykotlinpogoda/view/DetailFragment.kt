package com.example.mykotlinpogoda.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykotlinpogoda.model.Weather
import com.example.mykotlinpogoda.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle):DetailFragment{
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

        val weather = arguments?.getParcelable<Weather>("WEATHER_EXTRA")
        binding.cityName.text = weather?.city?.name ?: ""
        binding.temperature.text = weather?.temperature?.toString() ?: "0"


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
