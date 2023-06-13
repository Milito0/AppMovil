package com.appvuelos.ui.view.nav.historial.recyclerview_flight

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appvuelos.R
import com.appvuelos.ui.domain.model.FlightItem

class FlightAdapter(
    private var flightList: MutableList<FlightItem> = mutableListOf()
) : RecyclerView.Adapter<FlightViewHolder>() {

    fun updateList (flights: ArrayList<String>){
        val x = flights.size -2
        for(i in (0..x) step 9){
            Log.i("milito", i.toString())
            flightList.add(FlightItem(flights[i], flights[i+1],flights[i+2],flights[i+3],flights[i+4],flights[i+5],flights[i+6],flights[i+7],flights[i+8]))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flight, parent, false)
        return FlightViewHolder(view)
    }

    override fun getItemCount() = flightList.size

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.render(flightList[position])
    }
}