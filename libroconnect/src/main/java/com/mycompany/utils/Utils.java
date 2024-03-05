package com.mycompany.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    
    
    /*
    Método isNumeric(String strNum):

    Este método verifica si una cadena de texto es numérica.
    Comprueba si la cadena es nula y luego intenta convertirla a un entero. 
    Si la conversión es exitosa, devuelve true; de lo contrario, devuelve false.
    */
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /*
    Método getFechaActual():

    Este método devuelve la fecha actual en formato de cadena "dd-MM-yyyy".
    Utiliza la clase SimpleDateFormat para formatear la fecha actual en el 
    formato especificado y la devuelve como una cadena.
    */
    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }
    
    /*
    Método diferenciasDeFechas(Date fechaInicial, Date fechaFinal):

    Este método calcula la diferencia en días entre dos fechas.
    Convierte las fechas a formato de cadena y luego a objetos Date.
    Calcula la diferencia en milisegundos entre las dos fechas y luego la convierte a días.
    */
    
    //Diferencias entre dos fechas
    //@param fechaInicial La fecha de inicio
    //@param fechaFinal  La fecha de fin
    //@return Retorna el numero de dias entre dos fechas
    public static synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) throws ParseException {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        fechaFinal = df.parse(fechaFinalString);

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    
    /*
    Método stringToDate(String fecha):

    Este método convierte una cadena de texto en formato "dd-MM-yyyy" a un objeto Date.
    Utiliza un objeto SimpleDateFormat para realizar la conversión de la cadena a un objeto Date.
    */
    //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
    //@param La fecha a convertir a formato date
    //@return Retorna la fecha en formato Date
    public static synchronized java.util.Date stringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}