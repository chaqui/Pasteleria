package com.ati.queenspasteleria.Settings

import com.google.gson.Gson
import java.io.BufferedOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by josue on 24/01/18.
 */

//Clase para almacenar las configuraciones del sistema
class Settings(){
    companion object {
        val url =""
        var iniciadaSesion:Boolean = false

        fun enviarPorPost(datarecibida:String, direccionUrl:String ){
            var url = URL(direccionUrl)
            var con: HttpURLConnection

            //capturador de excepciones
            try{

                //prepaamos el data para el usuario

                var data = "body=" + URLEncoder.encode(datarecibida, "UTF-8")
                //Realizar la conexion
                con = url.openConnection() as HttpURLConnection

                //Activar metodo Post
                con.doOutput = true

                //tamaño previamente conocido
                con.setFixedLengthStreamingMode(data.length)

                con.setRequestProperty("Content-Type","application/x-www-form-urlencoded")

                var out =  BufferedOutputStream(con.getOutputStream())



            }
            catch(e:Exception){

            }
        }
    }
}