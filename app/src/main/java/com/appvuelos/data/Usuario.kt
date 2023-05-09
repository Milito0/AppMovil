package com.appvuelos.data

class Usuario {

    val name: String
    val pass: String
    lateinit var activeFlight: Vuelo
    lateinit var historyFlight: List<Vuelo>

    constructor(name: String, pass: String) {
        this.name = name
        this.pass = pass
    }



}