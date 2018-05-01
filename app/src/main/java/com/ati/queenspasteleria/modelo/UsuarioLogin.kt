package com.ati.queenspasteleria.modelo

import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.ati.queenspasteleria.Settings.ConfiguracionUsuario
import com.ati.queenspasteleria.Settings.Hash
import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by josue on 7/02/18.
 */

class UsuarioLogin(
        @SerializedName("user") var user: String,
        @SerializedName("password") var password:String


){
    fun loggear(context: Context){
        var result:String
        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            val urlString = Settings.url+"/logincliente"
            val url = URL(urlString)
            var urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestProperty("Content-Type", "application/json;  charset=UTF-8")
            urlConnection.setRequestProperty("Accept", "application/json")
            urlConnection.requestMethod="POST"

            var wr = DataOutputStream(urlConnection.outputStream)
            var json: String = crearJson()
            wr.writeBytes(json)
            wr.flush()

            var reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            var buffer = StringBuffer()
            var linea:String? = null
            Log.i("reader",reader.toString())
            while ({linea = reader.readLine(); linea}() != null){

                buffer.append(linea+"\n")
            }
            Log.i("Buffer",buffer.toString())
            var configuracionUsuario = ConfiguracionUsuario()
            configuracionUsuario.crearArchivoUsuario(buffer.toString(), context)
            wr.close()
            reader.close()

        }
        catch (e: Exception){

        }

    }

    fun verificar(): Boolean {
        var result:String
        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            val urlString = Settings.url+"/validadusuario"
            val url = URL(urlString)
            var urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestProperty("Content-Type", "application/json;  charset=UTF-8")
            urlConnection.setRequestProperty("Accept", "application/json")
            urlConnection.requestMethod="POST"

            var wr = DataOutputStream(urlConnection.outputStream)
            var json: String = crearJson()
            wr.writeBytes(json)
            wr.flush()

            var reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            var buffer = StringBuffer()
            var linea:String? = null
            Log.i("reader",reader.toString())
            while ({linea = reader.readLine(); linea}() != null){

                buffer.append(linea+"\n")
            }
            Log.i("Buffer",buffer.toString())
            Settings
            wr.close()
            reader.close()
            return buffer.toString().toBoolean()

        }
        catch (e: Exception){
            return false
        }
    }

    fun crearJson(): String {
        var gson = Gson()
        val hash = Hash()
        this.password = hash.sha1(this.password)!!
        var data = gson.toJson(this) as String
        Log.i("json login",data)
        return data

    }

    fun recuperarContrasenia(){
        Settings.enviarPorPost(crearJson(),Settings.url+"/verificarcorreo")
    }

}