package com.appvuelos.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.appvuelos.R
import com.appvuelos.data.Conexion
import com.appvuelos.data.Usuario
import com.appvuelos.databinding.ActivityMainBinding
import com.appvuelos.ui.view.nav.NavActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    companion object{
        lateinit var conn: Conexion
        lateinit var user: Usuario
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

        initComs()
    }

    private fun initComs() {
        val act = this
        CoroutineScope(Dispatchers.IO).launch {
            conn = Conexion(act)
            conn.run()
        }
    }

    fun navigateToSignIn() {
        navController.navigate(R.id.signinFragment)
    }
    fun navigateToLogIn() {
        navController.navigate(R.id.loginFragment)
    }
    fun navigateToHome() {
        val intent = Intent(this, NavActivity::class.java)
        startActivity(intent)
    }


}