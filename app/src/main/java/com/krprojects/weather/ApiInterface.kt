package com.krprojects.weather

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("data/2.5/weather")
    fun getCurrentCityWeatherInfo(
        @Query("q") city_name: String?,
        @Query("appid") appid: String?,
        @Query("units") units: String?
    ): Call<WeatherModel>
@GET("data/2.5/forecast")

fun getForecast(
    @Query("q") city_name: String?,
    @Query("appid") appid: String?,
    @Query("units") units: String?
):Call<ForecastModel>
    companion object {
        var q = "Jodhpur"
        val appid = "bebd9da120762f2b44fb9b1f056c90ef"
        val units = "metric"
        val BASE_URL = "https://api.openweathermap.org"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}