// src/main/kotlin/data/WeatherRepository.kt
package data

import domain.WeatherInfo
import kotlinx.browser.window
import kotlin.js.Promise

class WeatherRepository {

    // Promise-based function (no coroutines)
    fun getWeatherPromise(lat: Double, lon: Double): Promise<WeatherInfo> {
        val url = "https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&current_weather=true"

        return window.fetch(url)
            .then { res -> res.json() }
            .then { data ->
                val current = data.asDynamic().current_weather
                WeatherInfo(
                    temperature = current.temperature as Double,
                    windSpeed = current.windspeed as Double,
                    time = current.time as String
                )
            }
    }
}
