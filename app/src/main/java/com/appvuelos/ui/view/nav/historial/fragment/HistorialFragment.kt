package com.appvuelos.ui.view.nav.historial.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.appvuelos.databinding.FragmentHistorialBinding
import com.appvuelos.ui.view.nav.historial.recyclerview_flight.FlightAdapter

class HistorialFragment : Fragment() {

    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FlightAdapter
    private lateinit var list: java.util.ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            list = it.getStringArrayList("LIST")!!
        }

        initUI()

        adapter.updateList(list)
    }

    private fun initUI() {
        adapter = FlightAdapter()
        binding.rvHistorial.setHasFixedSize(true)
        binding.rvHistorial.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvHistorial.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistorialBinding.inflate(layoutInflater)
        return binding.root
    }

}