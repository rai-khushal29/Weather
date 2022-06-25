package com.krprojects.weather

data class WeatherModel(
    val coord: objCoord,
    val weather: List<weather>,
    val base: String,
    val main: objMain,
    val visibility: String,
    val wind: objWind,
    val clouds: objClouds,
    val dt: String,
    val sys: objSys,
    val timezome: String,
    val id: String,
    val name: String,
    val cod: String,
    val message: String,
    val cnt: String,
    val list: List<list>,
    val city: objCity
)

data class objCoord(val lon: String, val lat: String)

data class objClouds(val all: String)

data class weather(
    val id: String,
    val main: String,
    val description: String,
    val icon: String,
)

data class objMain(
    val temp: String,
    val feels_like: String,
    val temp_min: String,
    val temp_max: String,
    val pressure: String,
    val humidity: String,
    val sea_level: String,
    val grnd_level: String,
)

data class objWind(val speed: String, val deg: String, val gust: String)

data class objSys(val country: String, val sunrise: String, val sunset: String)

data class objCity(
    val id: String,
    val coord: objCoord,
    val country: String,
    val population: String,
    val timezone: String,
    val sunrise: String,
    val sunset: String,
)

data class list(
    val dt: String,
    val main: objMain,
    val weather: List<weather>,
    val clouds: objClouds,
    val wind: objWind,
    val visibility: String,
    val pop: String,
    val sys: objSys,
    val dt_txt: String,
)
