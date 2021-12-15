package com.example.mykotlinpogoda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinpogoda.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(MainViewModel::class.java)
            //подписались на изменения live data
        viewModel.getData().observe(viewLifecycleOwner, { state->
            render(state) })
        //запросили новые данные
        viewModel.getWeather()


    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success -> {
                binding.LinerLoading.visibility = View.GONE
                binding.cityName.text = state.weater.city
                binding.temperature.text = state.weater.temperature.toString()
            }
            is AppState.Error -> {
                binding.LinerLoading.visibility = View.VISIBLE
                Snackbar.make(binding.root, state.error.toString(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("попробовать снова") {
                        //запросили новые данные
                        viewModel.getWeather()
                    }.show()
            }
            is AppState.Loading ->
                binding.LinerLoading.visibility = View.VISIBLE


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}