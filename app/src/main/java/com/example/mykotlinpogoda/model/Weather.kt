package com.example.mykotlinpogoda.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
//автогенерация всех аннотаций(оч крутая штука!)
@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val filslick: Int = 0
):Parcelable

//fun getDefaultCity(): String{ return "Москва"}
//fun getDefaultCity(): String = "Москва"
fun getDefaultCity() = City("Москва", 55.755826, 37.61729900)
@Parcelize
data class City(
    val name: String = "Москва",
    val lat: Double = 0.0,
    val lon: Double = 0.0
):Parcelable

fun getWorldCities(): List<Weather> {
    return listOf(
        Weather(City("Лондон", 51.5085300, -0.1257400), 1, 2),
        Weather(City("Токио", 35.6895000, 139.6917100), 3, 4)
    )
}

fun getRussianCities(): List<Weather> {
    return listOf(
        Weather(City("Москва", 55.755826, 37.61729900), 1, 2),
        Weather(City("Екатеринбург", 56.838926, 60.6057025), 3, 4),
        Weather(City("Верхняя Пышма", 56.838926, 60.6057025), 3, 4),
        Weather(City("Ревда", 56.838926, 60.6057025), 3, 4),
        Weather(City("Тюмень", 56.838926, 60.6057025), 3, 4)
    )
}
