package com.appvuelos.ui.view.login

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.appvuelos.R
import com.appvuelos.data.Conexion
import com.appvuelos.databinding.ActivityMainBinding
import com.appvuelos.ui.view.nav.NavActivity
import com.appvuelos.ui.view.nav.NavActivity.Companion.bool
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        lateinit var conn: Conexion
        lateinit var correoUser: String
    }
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

//        initComs()
    }

    fun initComs() {
//        val act = this
//        CoroutineScope(Dispatchers.IO).launch {
//            conn = Conexion(act)
//            conn.run()
//        }
    }

    fun incorrectIP(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("IP Erronea/ Servidor no responde")
        builder.apply {
            setPositiveButton("Ok") { _, _ ->
            }

        }
        builder.create().show()
    }
    fun incorrectCredentials(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Credenciales erroneas")
        builder.apply {
            setPositiveButton("Ok") { _, _ ->
            }

        }
        builder.create().show()
    }
    fun navigateToSignIn() {
        navController.navigate(R.id.signinFragment)
    }

    fun navigateToLogIn() {
        navController.navigate(R.id.loginFragment)
    }

    fun navigateToHomeNoFlight() {
        val intent = Intent(this, NavActivity::class.java)
        intent.putExtra("BOOL", false)
        startActivity(intent)
    }

    fun navigateToHomeFlight(
        apellidos: String,
        nombre: String,
        bookingReference: String,
        siglasSalida: String,
        siglasLlegada: String,
        codeNombre: String,
        nVuelo: String,
        fechaSalida: String,
        fechaAterrizaje: String,
        asiento: String,
        sequenceNumber: String,
        passengerStatus: String,
        sizeField: String,
        version: String,
        checkinSource: String,
        variableField: String,
        internationalVerification: String,
        frequentFlyerData: String,
        airlineData: String,
        ciudadSalida: String,
        ciudadDestino: String,
        nombreCompany: String
    ) {

        val intent = Intent(this, NavActivity::class.java)
        val lista = arrayOf(
            apellidos,
            nombre,
            bookingReference,
            siglasSalida,
            siglasLlegada,
            codeNombre,
            nVuelo,
            fechaSalida,
            fechaAterrizaje,
            asiento,
            sequenceNumber,
            passengerStatus,
            sizeField,
            version,
            checkinSource,
            variableField,
            internationalVerification,
            frequentFlyerData,
            airlineData,
            ciudadSalida,
            ciudadDestino,
            nombreCompany
        )
        intent.putExtra("LISTA", lista)
        intent.putExtra("BOOL", true)
        startActivity(intent)
    }


}