package com.example.mykotlinpogoda

sealed class AppState {


    data class Success(val weater: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}