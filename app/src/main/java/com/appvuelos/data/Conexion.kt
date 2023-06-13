package com.appvuelos.data

import android.util.Log
import com.appvuelos.ui.view.login.MainActivity
import com.appvuelos.ui.view.login.MainActivity.Companion.correoUser
import com.appvuelos.ui.view.nav.NavActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.PrintStream
import java.lang.Exception
import java.net.Socket
import java.util.Scanner

class Conexion(activity: MainActivity, correo1: String, pass1: String, bool: Boolean, IP: String) : Runnable {

    lateinit var act2: NavActivity
    private val act = activity
    private var correo = correo1
    private var isSignin = bool
    private var pass = pass1
    private val LOCAL_IP = IP
    lateinit var socket: Socket
    lateinit var salida: PrintStream
    lateinit var entrada: Scanner
    lateinit var linea: String
    var desconectar = false
    var x = 0


    override fun run() {
        try {
            socket = Socket(LOCAL_IP, 23223)

        }catch (e: Exception){
            CoroutineScope(Dispatchers.Main).launch {
                act.incorrectIP()
            }
        }

        try {
            salida = PrintStream(socket.getOutputStream())
            entrada = Scanner(socket.getInputStream())
        } catch (e: IOException) {
            Log.i("Milito", e.toString())

        }
        if(isSignin){
            peticionSignin(correo, pass)
        }else {
            peticionLogin(correo, pass)
        }

        while (!desconectar) {
            try {
                linea = entrada.nextLine()

            } catch (e: NoSuchElementException) {
                Log.i("milito", e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    act2.navigateToLogin()
                }
                break;
            }

            var lineas = linea.split("#")
            Log.i("milito", lineas.toString())
            when (lineas[0]) {
                // ACEPTACION DE CONEXION TANTO POR SINGIN COMO LOGGIN
                "CONACP" -> {
                    correoUser = lineas[1]
                    peticionVueloActivo()
                }

                "DESCON" -> {
                    desconectar = !desconectar
                }

                "INFO" -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        act2.navigateToProfile(lineas[2], lineas[3], lineas[4], lineas[5])
                    }
                }

                "CONREF" -> {
                    desconectar = !desconectar
                    CoroutineScope(Dispatchers.Main).launch {
                        act.incorrectCredentials()
                    }
                }

                "HST" -> {
                    val list = ArrayList<String>()
                    val x = lineas.size - 1
                    for (i in (1..x)) {
                        list.add(lineas[i])
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        act2.navigateToHistorial(list)
                    }
                }

                "NOFLG" -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        if(x==0){
                            act.navigateToHomeNoFlight()
                            x=1
                        } else {
                            act2.navigateToHomeNoFlight()
                        }

                    }
                }

                "NOHST" -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        act2.navigateToNoHistorial()
                    }
                }

                "NEWFLG" -> {
                    peticionVueloActivo()
                }

                "FLG" -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        if(x==0){
                            act.navigateToHomeFlight(
                                lineas[1],
                                lineas[2],
                                lineas[3],
                                lineas[4],
                                lineas[5],
                                lineas[6],
                                lineas[7],
                                lineas[8],
                                lineas[9],
                                lineas[10],
                                lineas[11],
                                lineas[12],
                                lineas[13],
                                lineas[14],
                                lineas[15],
                                lineas[16],
                                lineas[17],
                                lineas[18],
                                lineas[19],
                                lineas[20],
                                lineas[21],
                                lineas[22]
                            )
                            x = 1
                        } else {
                            act2.navigateToHomeFlight(
                                lineas[1],
                                lineas[2],
                                lineas[3],
                                lineas[4],
                                lineas[5],
                                lineas[6],
                                lineas[7],
                                lineas[8],
                                lineas[9],
                                lineas[10],
                                lineas[11],
                                lineas[12],
                                lineas[13],
                                lineas[14],
                                lineas[15],
                                lineas[16],
                                lineas[17],
                                lineas[18],
                                lineas[19],
                                lineas[20],
                                lineas[21],
                                lineas[22]
                            )
                        }

                    }
                }
            }
        }
    }

    fun peticionVueloActivo() {
        salida.print("ACTFLG#$correoUser\r\n")
    }

    fun addActivity(act: NavActivity) {
        act2 = act
    }

    fun peticionSignin(nombreUsuario: String, password: String) {
        salida.print("NEWUSR#$nombreUsuario#$password\r\n")
    }

    fun peticionLogin(nombreUsuario: String, password: String) {
        salida.print("CONREQ#$nombreUsuario#$password\n")
    }

    fun peticionInfo(nombreUsuario: String) {
        salida.print("INFO#$nombreUsuario\r\n")
    }

    fun peticionHistorial(correoUser: String) {
        salida.print("HST#$correoUser\r\n")
    }

    fun peticionDesconexion(){
        salida.print("DESCON#\r\n")
    }

    fun actualizaDatos(nombre:String, apellidos:String, telefono:String, direccion:String){
        salida.print("UPDATA#$correoUser#$nombre#$apellidos#$telefono#$direccion#\r\n")
    }

    fun newFlight(results: String){
        salida.print("NEWFLG#$correoUser#$results\r\n")
    }

}





