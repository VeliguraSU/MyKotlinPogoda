package com.example.mykotlinpogoda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData()
    //следим за даннымми
    fun getData(): LiveData<AppState> = liveDataToObserver

    //запрашиваем данные
    fun getWeather(){
        liveDataToObserver.value = AppState.Loading

        Thread{
            Thread.sleep(500)

            if(Random.nextBoolean()){
                val weather = Weather("Омск", -10)
              liveDataToObserver.postValue(AppState.Success(weather))
            }else{
                liveDataToObserver.postValue(AppState.Error(Exception("сеть не пашет!")))
            }
        }
    }
}