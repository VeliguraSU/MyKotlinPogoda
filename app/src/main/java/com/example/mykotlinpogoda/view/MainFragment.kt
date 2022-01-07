package com.example.mykotlinpogoda.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinpogoda.R
import com.example.mykotlinpogoda.databinding.MainFragmentBinding
import com.example.mykotlinpogoda.mainmodel.AppState
import com.example.mykotlinpogoda.mainmodel.MainViewModel
import com.example.mykotlinpogoda.model.Weather
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?): DetailFragment {

            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = MainAdapter()
    private var isRussia = true
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.MainRecyclerView.adapter = adapter

//        binding.MainRecyclerView.layoutManager= LinerLayoutManager(requireActivity())

        adapter.listener = MainAdapter.OnItemClick { weather ->

            val bundel = Bundle()
            bundel.putParcelable("WEATHER_EXTRA", weather)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, DetailFragment.newInstance(bundel))
                .addToBackStack("")
                .commit()
        }


        //подписались на изменения live data
        viewModel.getData().observe(viewLifecycleOwner, { state ->
            render(state)
        })


        //запросили новые данные
        viewModel.getWeatherFromLocalStorageRus()

        binding.MainFAB.setOnClickListener {
            isRussia = !isRussia
            if (isRussia) {
                viewModel.getWeatherFromLocalStorageRus()
                binding.MainFAB.setImageResource(R.drawable.ic_baseline_outlined_flag_24)
            } else {
                viewModel.getWeatherFromLocalStorageWorld()
                binding.MainFAB.setImageResource(R.drawable.ic_baseline_flag_24)

            }
        }

    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {

                val weather: List<Weather> = state.data as List<Weather>
                adapter.setWeather(weather)
                binding.LinerLoading.visibility = View.GONE
            }
            is AppState.Error -> {
                binding.LinerLoading.visibility = View.VISIBLE
                Snackbar.make(binding.root, state.error.toString(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("попробовать снова") {
                        //запросили новые данные
                        viewModel.getWeatherFromLocalStorageRus()
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


