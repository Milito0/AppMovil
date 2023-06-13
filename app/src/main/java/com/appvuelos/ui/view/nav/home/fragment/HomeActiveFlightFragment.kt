package com.appvuelos.ui.view.nav.home.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appvuelos.R
import com.appvuelos.databinding.FragmentHomeActiveFlightBinding
import com.appvuelos.ui.domain.model.ActiveFlight
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class HomeActiveFlightFragment : Fragment() {

    private var _binding: FragmentHomeActiveFlightBinding? = null
    private val binding get() = _binding!!
    lateinit var flight: ActiveFlight


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val lista = it.getStringArray("LIST")
            flight = ActiveFlight(
                lista!![0],
                lista!![1],
                lista!![2],
                lista!![3],
                lista!![4],
                lista!![5],
                lista!![6],
                lista!![7],
                lista!![8],
                lista!![9],
                lista!![10],
                lista!![11],
                lista!![12],
                lista!![13],
                lista!![14],
                lista!![15],
                lista!![16],
                lista!![17],
                lista!![18],
                lista!![19],
                lista!![20],
                lista!![21]
            )
        }

        initUI()

    }

    private fun initUI() {
        var barcodeEncoder = BarcodeEncoder()
        var bitMap = barcodeEncoder.encodeBitmap(
            flight.apellidos + "#" + flight.nombre + "#" + flight.bookingReference + "#" + flight.siglasSalida + "#"
                    + flight.siglasLlegada + "#" + flight.codeNombre + "#" + flight.nVuelo + "#" + flight.fechaSalida + "#" + flight.fechaAterrizaje + "#"+ flight.asiento +"#"
                    + flight.sequenceNumber + "#" + flight.passengerStatus + "#" + flight.sizeField + "#" + flight.internationalVerification
                    + "#" + flight.frequentFlyerData + "#" + flight.airlineData,
            BarcodeFormat.QR_CODE,
            250, 250,
        )
//        var bitMap = barcodeEncoder.encodeBitmap(
//            "Apellidos#Nombre#E1AAAAA#LCG#AGP#VY#420#2023-07-10 14:00:00#2023-07-10 16:00:00#15f#73#3#59#420#1245678#123",
//            BarcodeFormat.QR_CODE,
//            250, 250,
//        )

        val lineas = flight.fechaSalida.split(" ")

        Log.i("patata", flight.sequenceNumber)
        binding.ivQR.setImageBitmap(bitMap)
        binding.asiento.text = "Asiento\n${flight.asiento}"
        binding.ciudadDestino.text = flight.ciudadDestino
        binding.ciudadSalida.text = flight.ciudadSalida
        binding.fecha.text = lineas[0]
        binding.company.text = flight.nombreCompany
        binding.nVuelo.text = "Numero vuelo\n"+flight.codeNombre+flight.nVuelo
        binding.siglasDestino.text = flight.siglasLlegada
        binding.siglasSalida.text = flight.siglasSalida
        binding.nombreCompleto.text = flight.nombre + " " + flight.apellidos
        binding.horaDespegue.text = lineas[1]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeActiveFlightBinding.inflate(layoutInflater)
        return binding.root
    }

}