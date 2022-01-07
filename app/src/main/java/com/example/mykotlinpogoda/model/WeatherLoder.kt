package com.example.mykotlinpogoda.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object WeatherLoder {


    private const val YOUR_API_KEY = "e087cd22-9ac5-4fc3-bd77-65692c1b8d4c"

    fun load(city: City, listner: OnWeatherLoadListner) {

        val handler = Handler(Looper.getMainLooper())

        Thread {
            var uriConnection: HttpsURLConnection? = null

            try {
                val uri =
                    URL("https://api.weather.yandex.ru/v2/informers?lat=${city.lat}&lon=${city.lon}")
                uriConnection = uri.openConnection() as HttpsURLConnection
                uriConnection.addRequestProperty("X-Yandex-API-Key", YOUR_API_KEY)
                uriConnection.requestMethod = "GET"
                uriConnection.readTimeout = 1000
                uriConnection.connectTimeout = 1000

                val reader = BufferedReader(InputStreamReader(uriConnection.inputStream))
                val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    reader.lines().collect(Collectors.joining("\n"))
                } else {
                    ""
                }

                Log.d("DEBUGLOG", "result:$result")

                val weatherDTO = Gson().fromJson<WeatherDTO>(result, WeatherDTO::class.java)
                handler.post {
                    listner.onLoded(weatherDTO)
                }

            } catch (e: Exception) {
                handler.post {
                    listner.onFaiLed(e)
                }

                Log.e("DEBUG_OG", "FAIL CONNACTION", e)
            } finally {
                uriConnection?.disconnect()
            }

        }.start()


    }

    interface OnWeatherLoadListner {
        fun onLoded(weatherDTO: WeatherDTO)
        fun onFaiLed(throwable: Throwable)

    }
}




