package com.example.mykotlinpogoda.model

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }
// возвращаем список погод Русских городов
    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }
// возвращаем список погод всяких мировых городов
    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }

}