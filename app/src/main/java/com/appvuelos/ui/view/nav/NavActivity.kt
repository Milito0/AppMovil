package com.appvuelos.ui.view.nav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.appvuelos.R
import com.appvuelos.databinding.ActivityNavBinding
import com.appvuelos.ui.view.login.MainActivity
import com.appvuelos.ui.view.login.MainActivity.Companion.conn
import com.appvuelos.ui.view.login.MainActivity.Companion.correoUser
import com.google.zxing.integration.android.IntentIntegrator

class NavActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavBinding
    private lateinit var navController: NavController
    private var myBundle: Bundle = Bundle()

    companion object{
        var bool = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.i("qr", data.toString())
        if (result != null) {
            Log.i("milito", result.contents.toString())
            if (result.contents == null) {
                Toast.makeText(this, "Error de lectura..", Toast.LENGTH_SHORT).show()
            } else {
                conn.newFlight(result.contents.toString())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bool = intent.extras!!.getBoolean("BOOL")

        conn.addActivity(this)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navigateToHome()

        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                binding.navBar.menu.getItem(0).itemId -> {
                    it.isChecked = true
                    conn.peticionVueloActivo()
                }
                binding.navBar.menu.getItem(1).itemId -> {
                    it.isChecked = true
                    conn.peticionHistorial(correoUser)
                }
                binding.navBar.menu.getItem(2).itemId -> {
                    it.isChecked = true
                    conn.peticionInfo(correoUser)
                }
            }
            false
        }
    }

    fun navigateToProfile(nombre:String, apellidos: String, telefono:String, direccion: String){
        val myBundle = Bundle()
        myBundle.putString("NOMBRE", nombre)
        myBundle.putString("APELLIDOS", apellidos)
        myBundle.putString("TELEFONO", telefono)
        myBundle.putString("DIRECCION", direccion)
        navController.navigate(R.id.profileFragment, myBundle)
    }

    fun navigateToHistorial(list: java.util.ArrayList<String>){
        val myBundle = Bundle()
        myBundle.putStringArrayList("LIST",list)
        navController.navigate(R.id.historialFragment, myBundle)
    }

    fun navigateToNoHistorial(){
        navController.navigate(R.id.noHistorialFragment)
    }

    fun navigateToHome(){

        if(!bool){
            navController.navigate(R.id.homeNoFlightFragment)
        } else {
            myBundle.putStringArray("LIST", intent.extras!!.getStringArray("LISTA"))
            navController.navigate(R.id.homeActiveFlightFragment, myBundle)
        }
    }

    fun navigateToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}