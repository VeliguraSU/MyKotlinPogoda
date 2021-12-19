package com.example.mykotlinpogoda.mainmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlinpogoda.model.Repository
import com.example.mykotlinpogoda.model.RepositoryImpl

class MainViewModel : ViewModel() {
    val repo: Repository = RepositoryImpl()
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData()

    //следим за даннымми
    fun getData(): LiveData<AppState> = liveDataToObserver

    fun getWeatherFromLocalStorageRus() = getDataFromLocalStorage(true)
    fun getWeatherFromLocalStorageWorld() = getDataFromLocalStorage(false)
    fun getWeatherFromRemoteSourse() = getDataFromLocalStorage(true)

    private fun getDataFromLocalStorage(isRussia: Boolean = true) {
     liveDataToObserver.value = AppState.Loading

        Thread {
            Thread.sleep(1000)



                val weather =if (isRussia){
                    repo.getWeatherFromLocalStorageRus()
                } else{
                    repo.getWeatherFromLocalStorageWorld()}
                liveDataToObserver.postValue(AppState.Success(weather))

        }.start()
    }
}

