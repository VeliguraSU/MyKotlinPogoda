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
        fun newInstance(bundel: Bundle) = DetailFragment()
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

//        viewModel = ViewModelProvider(this)
//            .get(DetailViewModel::class.java)
//            //подписались на изменения live data
//        viewModel.getData().observe(viewLifecycleOwner, { state->
//            render(state as AppState) })
//        //запросили новые данные
//        viewModel.getData()
//
//
//    }
////
////    private fun render(state: AppState) {
//        when (state) {
//            is AppState.Success<*> -> {
//
//                val weather=state.data as Weather
//
//                binding.LinerLoading.visibility = View.GONE
//                binding.cityName.text = weather.city.name
//                binding.temperature.text = weather.temperature.toString()
//            }
//            is AppState.Error -> {
//                binding.LinerLoading.visibility = View.VISIBLE
//                Snackbar.make(binding.root, state.error.toString(), Snackbar.LENGTH_INDEFINITE)
//                    .setAction("попробовать снова") {
//                        //запросили новые данные
//                        viewModel.getData()
//                    }.show()
//            }
//            is AppState.Loading ->
//                binding.LinerLoading.visibility = View.VISIBLE
//
//
//        }
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}