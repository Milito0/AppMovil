package com.appvuelos.ui.view.nav.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.appvuelos.R
import com.appvuelos.databinding.FragmentHomeNoActiveBinding
import com.appvuelos.ui.view.login.MainActivity.Companion.conn
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator

class HomeNoActiveFragment : Fragment() {

    private var _binding: FragmentHomeNoActiveBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.btnScan.setOnClickListener {
            initScanner()
        }
    }

    private fun initScanner() {


        val integrator = IntentIntegrator(this.activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.initiateScan()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        Log.i("qr", data.toString())
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        Log.i("qr", data.toString())
//        if (result != null) {
//            Log.i("milito", result.toString())
//            if (result.contents == null) {
//                Toast.makeText(this.context, "Error de lectura..", Toast.LENGTH_SHORT).show()
//            } else {
//                conn.newFlight(result.contents.toString())
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }


//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeNoActiveBinding.inflate(layoutInflater)
        return binding.root
    }

}