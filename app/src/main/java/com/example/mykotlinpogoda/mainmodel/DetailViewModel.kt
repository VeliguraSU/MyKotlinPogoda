package com.example.mykotlinpogoda.mainmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlinpogoda.model.Weather
import com.example.mykotlinpogoda.model.getDefaultCity

class DetailViewModel : ViewModel() {

private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData()
//следим за даннымми
fun getData(): LiveData<AppState> = liveDataToObserver

fun getWeather(){
    liveDataToObserver.value = AppState.Loading

    Thread{
        Thread.sleep(1000)


            val weather = Weather(getDefaultCity(), -10)
            liveDataToObserver.postValue(AppState.Success(weather))

        }
    }
}
