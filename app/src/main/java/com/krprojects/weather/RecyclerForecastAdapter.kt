package com.krprojects.weather

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krprojects.weather.RecyclerForecastAdapter.ViewHolder
import kotlinx.android.synthetic.main.forecast_row.view.*

class RecyclerForecastAdapter(val context: Context, val arrData: ArrayList<forecast_row_model>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time = itemView.txt_time
        val icon = itemView.weather_ico
        val temp = itemView.fr_temp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.forecast_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.txt_time.text = arrData[position].time
        val url = "https://openweathermap.org/img/wn/" + arrData[position].icon + "@2x.png"
        Glide.with(context).load(url).into(holder.itemView.weather_ico)
        holder.itemView.fr_temp.text = arrData[position].temp
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

}