package com.appvuelos.ui.domain.model

data class FlightItem(
    var siglasSalida: String,
    var salida: String,
    var siglasLlegada: String,
    var llegada: String,
    var fecha: String,
    var company: String,
    var asiento: String,
    var latitud: String,
    var longitud: String,
)