//import kotlinx.browser.window
//import react.*
//import react.dom.html.ReactHTML
//import web.cssom.ClassName
//import kotlin.js.Promise
//
//external interface WeatherResponse {
//    val latitude: Double
//    val longitude: Double
//    val current_weather: dynamic
//}
//
//// Browser API declarations
//external interface Navigator {
//    val geolocation: Geolocation
//}
//
//external interface Geolocation {
//    fun getCurrentPosition(
//        success: (Position) -> Unit,
//        error: (PositionError) -> Unit
//    )
//}
//
//external interface Position {
//    val coords: Coordinates
//}
//
//external interface Coordinates {
//    val latitude: Double
//    val longitude: Double
//}
//
//external interface PositionError {
//    val message: String
//}
//
//fun fetchWeather(lat: Double, lon: Double): Promise<WeatherResponse> {
//    console.log("Lat:$lat & log:$lon")
//    val url = "https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&current_weather=true"
//    return window.fetch(url)
//        .then { response -> response.json() }
//        .then { data -> data.unsafeCast<WeatherResponse>() }
//}
//
//val App = FC<Props> {
//    var weather by useState<WeatherResponse?>(null)
//    var loading by useState(true)
//
//    fun loadWeather() {
//        loading = true
//        window.navigator.unsafeCast<Navigator>().geolocation.getCurrentPosition(
//            { pos: Position ->
//                fetchWeather(pos.coords.latitude, pos.coords.longitude)
//                    .then { data ->
//                        weather = data
//                        console.log("api data:", data)
//                        loading = false
//                    }
//                    .catch { error ->
//                        console.error("Error:", error)
//                        loading = false
//                    }
//            },
//            { error: PositionError ->
//                console.error("Error: ${error.message}")
//                loading = false
//            }
//        )
//    }
//
//    // Fetch once when the app starts
//    useEffectOnce { loadWeather() }
//
//    ReactHTML.div {
//        className = ClassName(
//            "flex flex-col items-center justify-center min-h-screen " +
//                    "bg-gradient-to-br from-blue-400 via-blue-500 to-indigo-600 " +
//                    "text-white font-sans p-6"
//        )
//
//        ReactHTML.div {
//            className = ClassName(
//                "bg-white/10 backdrop-blur-lg rounded-2xl shadow-2xl p-8 " +
//                        "text-center transform transition-all duration-500 animate-fadeIn"
//            )
//
//            if (loading) {
//                ReactHTML.p {
//                    className = ClassName("text-xl font-semibold animate-pulse")
//                    +"🌎 Fetching your weather..."
//                }
//            } else if (weather != null) {
//                val current = weather!!.current_weather
//
//                ReactHTML.h2 {
//                    className = ClassName("text-3xl font-bold mb-4 drop-shadow-lg")
//                    +"📍 Your Weather"
//                }
//
//                ReactHTML.div {
//                    className = ClassName("flex flex-col gap-3 text-lg")
//
//                    ReactHTML.p {
//                        +"🌡 Temperature: ${current.temperature}°C"
//                    }
//                    ReactHTML.p {
//                        +"💨 Wind Speed: ${current.windspeed} km/h"
//                    }
//                    ReactHTML.p {
//                        +"🕒 Time: ${current.time}"
//                    }
//                }
//            } else {
//                ReactHTML.p {
//                    className = ClassName("text-red-300 font-semibold")
//                    +"⚠ Could not fetch weather data"
//                }
//            }
//
//            ReactHTML.button {
//                className = ClassName(
//                    "mt-6 px-6 py-3 bg-yellow-400 text-black font-semibold " +
//                            "rounded-full shadow-lg hover:bg-yellow-300 transition " +
//                            "hover:scale-105 active:scale-95 flex items-center gap-2"
//                )
//                onClick = { loadWeather() }
//                +"🔄 Refresh"
//            }
//        }
//    }
//}
//
package presentation

import data.WeatherRepository
import domain.WeatherInfo
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML
import web.cssom.ClassName

// Browser API declarations
external interface Navigator {
    val geolocation: Geolocation
}
external interface Geolocation {
    fun getCurrentPosition(
        success: (Position) -> Unit,
        error: (PositionError) -> Unit
    )
}
external interface Position {
    val coords: Coordinates
}
external interface Coordinates {
    val latitude: Double
    val longitude: Double
}
external interface PositionError {
    val message: String
}

val App = FC<Props> {
    var weather by useState<WeatherInfo?>(null)
    var loading by useState(true)
    var error by useState<String?>(null)

    val repo = WeatherRepository()

    fun loadWeather() {
        loading = true
        error = null

        window.navigator.unsafeCast<Navigator>().geolocation.getCurrentPosition(
            { pos: Position ->
                // Call repository with Promises instead of coroutines
                repo.getWeatherPromise(pos.coords.latitude, pos.coords.longitude)
                    .then { data ->
                        weather = data
                        loading = false
                    }
                    .catch { err ->
                        error = err.message
                        loading = false
                    }
            },
            { err: PositionError ->
                error = err.message
                loading = false
            }
        )
    }

    // fetch once when the component mounts
    useEffectOnce { loadWeather() }

    ReactHTML.div {
        className = ClassName(
            "flex flex-col items-center justify-center min-h-screen " +
                    "bg-gradient-to-br from-blue-400 via-blue-500 to-indigo-600 text-white p-6"
        )

        ReactHTML.div {
            className = ClassName("bg-white/10 backdrop-blur-lg rounded-2xl shadow-2xl p-8 text-center")

            when {
                loading -> ReactHTML.p {
                    className = ClassName("text-xl font-semibold animate-pulse")
                    +"🌎 Fetching your weather..."
                }
                error != null -> ReactHTML.p {
                    className = ClassName("text-red-300 font-semibold")
                    +"⚠ Error: $error"
                }
                weather != null -> {
                    ReactHTML.h2 {
                        className = ClassName("text-3xl font-bold mb-4")
                        +"📍 Your Weather"
                    }
                    ReactHTML.p { +"🌡 Temperature: ${weather!!.temperature}°C" }
                    ReactHTML.p { +"💨 Wind Speed: ${weather!!.windSpeed} km/h" }
                    ReactHTML.p { +"🕒 Time: ${weather!!.time}" }
                }
            }

            ReactHTML.button {
                className = ClassName(
                    "mt-6 px-6 py-3 bg-yellow-400 text-black font-semibold " +
                            "rounded-full shadow-lg hover:bg-yellow-300 transition " +
                            "hover:scale-105 active:scale-95 flex items-center gap-2"
                )
                onClick = { loadWeather() }
                +"🔄 Refresh"
            }
        }
    }
}
