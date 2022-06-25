package com.krprojects.weather

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_select_city.*
import java.util.prefs.Preferences

class select_city : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_city)
        val myDb = MyDBHelper(this)
        val sp: SharedPreferences = this.getSharedPreferences("name", MODE_PRIVATE)
        val editor = sp.edit()

        if (!sp.contains("name")) {
            myDb.apply {
                //addData(CitiesModel("Current Location"))
                addData(CitiesModel("Jodhpur"))
                addData(CitiesModel("Jaipur"))
            }

            editor.putString("name", "")
            editor.apply()
        }

        val arrData: ArrayList<CitiesModel> = myDb.fetchData()

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@select_city)
            adapter = RecyclerCityAdapter(this@select_city, arrData)
        }
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}