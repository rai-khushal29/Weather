package com.krprojects.weather

data class ForecastModel(
    val cod: String,
    val message: String,
    val cnt: String,
    val list: List<list>,
    val city: objCity
)

/*data class ForecastList(
    val dt: String,
    val main: objMain,
    val weather: List<weather>,
    val clouds: objClouds,
    val wind: objWind,
    val visibility: String,
    val pop: String,
    val sys: objSys
)*/

/*
data class ObjCity(
    val id: String,
    val name: String,
    val coord: objCoord,
    val country: String,
    val population: String,
    val timezone: String,
    val sunrise: String,
    val sunset: String
)
*/
