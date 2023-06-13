package com.appvuelos.ui.view.nav.historial.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appvuelos.R
import com.appvuelos.databinding.FragmentHistorialBinding
import com.appvuelos.databinding.FragmentNoHistorialBinding

private var _binding: FragmentNoHistorialBinding? = null
private val binding get() = _binding!!

class NoHistorialFragment : Fragment() {

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initUI()
//    }
//
//    private fun initUI() {
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoHistorialBinding.inflate(layoutInflater)
        return  binding.root
    }

}