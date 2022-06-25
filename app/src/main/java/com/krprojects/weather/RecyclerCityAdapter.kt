package com.krprojects.weather

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krprojects.weather.ApiInterface.Companion.appid
import com.krprojects.weather.ApiInterface.Companion.q
import com.krprojects.weather.ApiInterface.Companion.units
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_row.view.*

class RecyclerCityAdapter(val context: Context, val arrData: ArrayList<CitiesModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView.cityName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.city_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.cityName.text=arrData[position].name
        if(holder.itemView.cityName.text=="Jodhpur")
        {
            holder.itemView.city_row.setBackgroundResource(R.drawable.jd_city_row)
        }
        if(holder.itemView.cityName.text=="Jaipur")
        {
            holder.itemView.city_row.setBackgroundResource(R.drawable.jaipur_city_row)
        }
        holder.itemView.setOnClickListener {
            q=arrData[position].name
            Log.d("City",q)
            context.startActivity(Intent(context,MainActivity::class.java))
            

        }
    }

    override fun getItemCount(): Int {
        return arrData.size
    }
}