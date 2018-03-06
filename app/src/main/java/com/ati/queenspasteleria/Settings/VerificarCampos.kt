package com.ati.queenspasteleria.Settings

import android.app.AlertDialog
import android.content.Context
import com.ati.queenspasteleria.excepciones.ExcepcionCampoVacio
import com.ati.queenspasteleria.excepciones.ExcepcionTamanioCadena

/**
 * Created by josue on 2/02/18.
 */
class VerificarCampos(){
    //funcion para verificar si estan vacios los elementos
    //la cadena incluye los elementos del campo
    fun verificarSiEstaVacio(cadena:String, nombreDelCampo:String): String {
        // comprobamos la cadena si esta vacia enviamos la excepción
        if (cadena.equals("")){
            var excepcion = ExcepcionCampoVacio()
            excepcion.campo =nombreDelCampo //ingresamos el nombre del campo para la excepcion
            throw  excepcion
        }
        return cadena //retornamos la cadena si no esta vacia
    }

    fun verificarTamanioCadena(cadena:String, campo:String, tamanio:Int):String{
        if(cadena.length != tamanio){
            var excepcion = ExcepcionTamanioCadena()
            excepcion.campo = campo
            throw  excepcion
        }
        return cadena
    }
    fun mostrarMensajeDeError(mensaje:String,context: Context){
        var mensajeDeAlerta  = AlertDialog.Builder(context).create()
        mensajeDeAlerta.setTitle("error")
        mensajeDeAlerta.setMessage(mensaje)
        mensajeDeAlerta.setButton(AlertDialog.BUTTON_POSITIVE,"OK",{
            dialogInterface, i ->
        })
    }
}