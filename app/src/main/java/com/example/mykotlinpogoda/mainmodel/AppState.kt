package com.example.mykotlinpogoda.mainmodel

sealed class AppState {


    data class Success<T>(val data: T)  : AppState()
    //Notching это "ничего" ну типа не использовать дженерик
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}