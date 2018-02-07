package com.ati.queenspasteleria.Settings

import com.google.gson.Gson
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
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

        fun enviarPorPost(datosAEnviar:String, direccionUrl:String ){
            var url = URL(direccionUrl)
            var con: HttpURLConnection

            //capturador de excepciones
            try{

                //prepaamos el data para el usuario

                var data = "body=" + URLEncoder.encode(datosAEnviar, "UTF-8")
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
        fun recibirInfo(datosAEnviar:String, direccionUrl:String): String? {
            var result:String
            try {
                var url = URL(direccionUrl)
                var urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod ="GET"
                urlConnection.connect()
                var inputStream = urlConnection.inputStream
                var buffer = StringBuffer()
                if (inputStream == null){
                    return null
                }
                var reader = BufferedReader( InputStreamReader(inputStream))

                var linea:String? = null

                while ({linea = reader.readLine(); linea}() != null){
                    buffer.append(linea+"\n")
                }

                if (buffer.length ==0){
                    return null
                }
                result = buffer.toString()
                if(urlConnection != null){
                    urlConnection.disconnect()
                }
                return result
            }
            catch(e: IOException){
                return null
            }



        }
    }
}