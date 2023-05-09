package com.appvuelos.data

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.appvuelos.R
import com.appvuelos.ui.view.login.MainActivity
import com.google.android.play.integrity.internal.x
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.PrintStream
import java.lang.IllegalArgumentException
import java.net.InetAddress
import java.net.Socket
import java.util.Scanner

class Conexion(activity: MainActivity) : Runnable {

    private val act = activity
    private val LOCAL_IP = "172.20.10.2"
    lateinit var socket: Socket
    lateinit var salida: PrintStream
    lateinit var entrada: Scanner
    lateinit var linea: String
    var desconectar = false


    override fun run() {
        try {
            socket = Socket(LOCAL_IP, 23223)
        } catch (e: IOException) {
            Log.i("Milito", e.toString())
        } catch (e: IllegalArgumentException) {
            Log.i("Milito", e.toString())
        }

        try {
            salida = PrintStream(socket.getOutputStream())
            entrada = Scanner(socket.getInputStream())
        } catch (e: IOException) {
            Log.i("Milito", e.toString())
        }

        while (!desconectar) {
            try {
                linea = entrada.nextLine()

            } catch (e: NoSuchElementException) {
                Log.i("milito", e.toString())
                break;
            }

            var lineas = linea.split("#")
            Log.i("milito", lineas.toString())
            when (lineas[0]) {
                // ACEPTACION DE CONEXION TANTO POR SINGIN COMO LOGGIN
                "CONACP" -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        act.navigateToHome()
                    }
                }
                "DESCON" -> {
                    desconectar = !desconectar
                }
            }
        }
    }

    fun peticionSignin(nombreUsuario: String, password: String) {
        salida.print("NEWUSR#$nombreUsuario#$password\r\n")
    }

    fun peticionLogin(nombreUsuario: String, password: String) {
        salida.print("CONREQ#$nombreUsuario#$password\n")
    }

}





