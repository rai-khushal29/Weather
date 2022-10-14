package com.krprojects.weather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.krprojects.weather.ApiInterface.Companion.appid
import com.krprojects.weather.ApiInterface.Companion.q
import com.krprojects.weather.ApiInterface.Companion.units
import io.nlopez.smartlocation.OnLocationUpdatedListener
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()  {
    lateinit var mLocManager:LocationManager
    lateinit var mLocListener:LocationListener
    val arrLoc = ArrayList<LocationModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (checkPer())
        {
            Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()

            this.doYourWork()


        }
        else{
            reqPer(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),200)

        }
       /* fun doYourWork(){
            ApiInterface.create().getCurrentCityWeatherInfo(q, appid, units)
                .enqueue(object : Callback<WeatherModel> {
                    override fun onResponse(
                        call: Call<WeatherModel>,
                        response: Response<WeatherModel>
                    ) {
                        if (response.code() == 200) {
                            val weatherResponse = response.body()
                            pbar.visibility = View.GONE
                            cityname.text = weatherResponse?.name.toString()
                            if (cityname.text == "Jodhpur") {
                                homebg.setBackgroundResource(R.drawable.jodhpur_day)
                            }
                            if(cityname.text=="Jaipur")
                                homebg.setBackgroundResource(R.drawable.jaipur_day)
                            desc.text = weatherResponse!!.weather[0].description.toString()
                            desc_temp.text =
                                weatherResponse!!.main?.temp.toString() + 0x00B0.toChar() + "C"
                            desc_fl.text =
                                weatherResponse!!.main?.feels_like.toString() + 0x00B0.toChar() + "C"
                            desc_mintemp.text =
                                weatherResponse!!.main?.temp_min.toString() + 0x00B0.toChar() + "C"
                            desc_maxtemp.text =
                                weatherResponse!!.main?.temp_max.toString() + 0x00B0.toChar() + "C"
                            desc_pressure.text = weatherResponse!!.main?.pressure.toString()
                            desc_humidity.text = weatherResponse!!.main?.humidity.toString()
                            temp.text = weatherResponse?.main?.temp.toString() + 0x00B0.toChar() + "C"
                            card_desc.visibility = View.VISIBLE
                            btn_select_city.visibility = View.VISIBLE

                            Log.d("City ", weatherResponse?.name.toString())

                        }
                        if (response.code() == 404) {
                            pbar.visibility = View.GONE
                            Toast.makeText(this@MainActivity, "Data Not Found", Toast.LENGTH_SHORT)
                                .show()
                        }
                        Log.d("Res", response.code().toString())

                    }

                    override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                        pbar.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity,
                            "Please check your internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            ApiInterface.create().getForecast(q, appid, units)
                .enqueue(object : Callback<ForecastModel> {
                    override fun onResponse(
                        call: Call<ForecastModel>,
                        response: Response<ForecastModel>
                    ) {
                        if (response.code() == 200) {
                            val forecastResponse = response.body()
                            hour_forecast.visibility=View.VISIBLE
                            fragval = forecastResponse!!.list.size
                            for (i in 0 until fragval) {
                                arrForecast.apply {
                                    add(
                                        forecast_row_model(
                                            forecastResponse.list[i].dt_txt.toString(),
                                            forecastResponse.list[i].weather[0].icon,
                                            forecastResponse.list[i].main.temp + 0x00B0.toChar() + "C"
                                        )
                                    )
                                }
                            }
                            recyclerhour.apply {
                                layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                                adapter=RecyclerForecastAdapter(this@MainActivity,arrForecast)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ForecastModel>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Response Failure", Toast.LENGTH_SHORT).show()
                    }

                })
        }*/


//        btn_select_city.setOnClickListener {
//            startActivity(Intent(this, select_city::class.java))
//        }


    }





    fun checkPer():Boolean
    {
        val checkLoc = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        return checkLoc==PackageManager.PERMISSION_GRANTED
    }
    fun reqPer(permissions: Array<String>, reqCode:Int)
    {
        ActivityCompat.requestPermissions(this,permissions,reqCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode)
        {
            200 ->
            {
                val result1 = grantResults[0]==PackageManager.PERMISSION_GRANTED
                val result2=grantResults[1]==PackageManager.PERMISSION_GRANTED
                if(result1&&result2)
                {
                    this.doYourWork()
                }
                else{
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    pbar.visibility=View.GONE
                }
            }
        }
    }


}

private fun MainActivity.doYourWork() {
    val myDb = MyDBHelper(this)
    val arrForecast = ArrayList<forecast_row_model>()
    var fragval = 0
/*if(SmartLocation.with(applicationContext).location().state().locationServicesEnabled()
    && SmartLocation.with(applicationContext).location().state().isAnyProviderAvailable()
    && SmartLocation.with(applicationContext).location().state().isGpsAvailable()
    && SmartLocation.with(applicationContext).location().state().isNetworkAvailable())
{
    SmartLocation.with(this).location().continuous().start(OnLocationUpdatedListener {
        Log.d("MyLocation", "lat: " + it.getLatitude() + ", lng: " + it.getLongitude())
    })
}*/
    ApiInterface.create().getCurrentCityWeatherInfo(q, appid, units)
        .enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()
                    pbar.visibility = View.GONE
                    cityname.text = weatherResponse?.name.toString()
                    if (cityname.text == "Jodhpur") {
                        homebg.setBackgroundResource(R.drawable.jodhpur_day)
                    }
                    if(cityname.text=="Jaipur")
                        homebg.setBackgroundResource(R.drawable.jaipur_day)
                    desc.text = weatherResponse!!.weather[0].description.toString()
                    desc_temp.text =
                        weatherResponse!!.main?.temp.toString() + 0x00B0.toChar() + "C"
                    desc_fl.text =
                        weatherResponse!!.main?.feels_like.toString() + 0x00B0.toChar() + "C"
                    desc_mintemp.text =
                        weatherResponse!!.main?.temp_min.toString() + 0x00B0.toChar() + "C"
                    desc_maxtemp.text =
                        weatherResponse!!.main?.temp_max.toString() + 0x00B0.toChar() + "C"
                    desc_pressure.text = weatherResponse!!.main?.pressure.toString()
                    desc_humidity.text = weatherResponse!!.main?.humidity.toString()
                    temp.text = weatherResponse?.main?.temp.toString() + 0x00B0.toChar() + "C"
                    card_desc.visibility = View.VISIBLE
                    btn_select_city.visibility = View.VISIBLE

                    Log.d("City ", weatherResponse?.name.toString())

                }
                if (response.code() == 404) {
                    pbar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Data Not Found", Toast.LENGTH_SHORT)
                        .show()
                }
                Log.d("Res", response.code().toString())

            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                pbar.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    "Please check your internet Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    ApiInterface.create().getForecast(q, appid, units)
        .enqueue(object : Callback<ForecastModel> {
            override fun onResponse(
                call: Call<ForecastModel>,
                response: Response<ForecastModel>
            ) {
                if (response.code() == 200) {
                    val forecastResponse = response.body()
                    hour_forecast.visibility=View.VISIBLE
                    fragval = forecastResponse!!.list.size
                    for (i in 0 until fragval) {
                        arrForecast.apply {
                            add(
                                forecast_row_model(
                                    forecastResponse.list[i].dt_txt.toString(),
                                    forecastResponse.list[i].weather[0].icon,
                                    forecastResponse.list[i].main.temp + 0x00B0.toChar() + "C"
                                )
                            )
                        }
                    }
                    recyclerhour.apply {
                        layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
                        adapter=RecyclerForecastAdapter(applicationContext,arrForecast)
                    }
                }
            }

            override fun onFailure(call: Call<ForecastModel>, t: Throwable) {
                Toast.makeText(applicationContext, "Response Failure", Toast.LENGTH_SHORT).show()
            }

        })
    btn_select_city.setOnClickListener {
        startActivity(Intent(applicationContext,select_city::class.java))
    }
}
