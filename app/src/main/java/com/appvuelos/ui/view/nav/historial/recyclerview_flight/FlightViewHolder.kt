package com.appvuelos.ui.view.nav.historial.recyclerview_flight

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appvuelos.databinding.ItemFlightBinding
import com.appvuelos.ui.domain.model.FlightItem

class FlightViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemFlightBinding.bind(view)

    fun render (flight: FlightItem){
        binding.tvSiglasSalida.text = flight.siglasSalida
        binding.tvSalida.text = flight.salida
        binding.tvSiglasLlegada.text = flight.siglasLlegada
        binding.tvLlegada.text = flight.llegada
        binding.tvFecha.text = flight.fecha
        binding.tvCompany.text = flight.company
        binding.asiento.text = flight.asiento
    }
}