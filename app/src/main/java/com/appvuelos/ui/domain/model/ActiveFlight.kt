package com.appvuelos.ui.domain.model

data class ActiveFlight (
    val apellidos: String,
    val nombre: String,
    val bookingReference: String,
    val siglasSalida: String,
    val siglasLlegada: String,
    val codeNombre: String,
    val nVuelo: String,
    val fechaSalida: String,
    val fechaAterrizaje: String,
    val asiento: String,
    val sequenceNumber: String,
    val passengerStatus: String,
    val sizeField: String,
    val version: String,
    val checkinSource: String,
    val variableField: String,
    val internationalVerification: String,
    val frequentFlyerData: String,
    val airlineData: String,
    val ciudadSalida: String,
    val ciudadDestino: String,
    val nombreCompany: String
        )

/**
String apellidos = rs.getString(1);
String nombre = rs.getString(2);
String bookingReference = rs.getString(3);
String siglasSalida = rs.getString(4);
String siglasLlegada = rs.getString(5);
String codeNombre = rs.getString(6);
int nVuelo = rs.getInt(7);
String fecha = rs.getString(8);
String asiento = rs.getString(9);
String sequenceNumber = rs.getString(10);
String passengerStatus = rs.getString(11);
String sizeField = rs.getString(12);
String version = rs.getString(13);
String checkinSource = rs.getString(14);
String variableField = rs.getString(15);
String internationalVerification = rs.getString(16);
int frequentFlyerData = rs.getInt(17);
String airlineData = rs.getString(18);
 */