package domain

interface WeatherUseCase {
    suspend fun getWeather(lat: Double, lon: Double): WeatherInfo
}
