package com.ati.queenspasteleria.Settings

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder



/**
 * Created by josue on 24/01/18.
 */

//Clase para almacenar las configuraciones del sistema
class Settings(){
    companion object {
        val host = "192.168.1.2"
        val puerto =""
        val url ="http://"+host+puerto+"/api/index.php"

        val urlImagen ="http://www.hdfondos.org/file/28225/728x410/16:9/rosquillas-pasteles_1292467610.jpg"
        var iniciadaSesion:Boolean = false

        fun enviarPorPost(datosAEnviar:String, direccionUrl:String ):Boolean{

            Log.i("datos a enviar",datosAEnviar)
            //capturador de excepciones
            try{
                var url = URL(direccionUrl)
                var urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.doOutput= true
                urlConnection.doInput = true
                urlConnection.setRequestProperty("Content-Type", "application/json;  charset=UTF-8")
                urlConnection.setRequestProperty("Accept", "application/json")
                urlConnection.requestMethod ="POST"

                var wr = DataOutputStream(urlConnection.outputStream)

                wr.writeBytes(datosAEnviar)
                wr.flush()

                var reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                var buffer = StringBuffer()
                var linea:String? = null

                while ({linea = reader.readLine(); linea}() != null){
                    buffer.append(linea+"\n")
                }
                Log.i("Buffer",buffer.toString())

                wr.close()
                reader.close()
                return true
            }
            catch(e:Exception){
                Log.e("excepcion",e.toString())
                return  false
            }

        }
        fun recibirInfo( direccionUrl:String): String? {

            var result:String
            var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            try {
                val urlString = direccionUrl+"/0"
                Log.i("url",urlString)
                var url = URL(urlString)
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
        fun recibirImagen(url:String): Bitmap? {
            val urlImagen = URL(urlImagen+url)
            var conImagen = urlImagen.openConnection() as HttpURLConnection
            conImagen.connect()
            var bitmap = BitmapFactory.decodeStream(conImagen.inputStream)
            return bitmap

        }
        fun getInetAddressByName( name:String){

        }
    }
}