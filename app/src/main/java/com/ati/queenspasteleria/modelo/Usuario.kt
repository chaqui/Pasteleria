package com.ati.queenspasteleria.modelo

import android.util.Log
import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by josue on 18/12/17.
 */
 class Usuario (
        @SerializedName("idCLiente")  var idCliente: Int,
        @SerializedName("nombre") var nombre_cli: String,
        @SerializedName("apellido") var apellido_cli: String,
        @SerializedName("direccion") var direccion_cli:String,
        @SerializedName("telefono") var telefono_cli:String,
        @SerializedName("nickname") var nickname_cli: String,
        @SerializedName("password") var password_cli: String,
        @SerializedName("correo") var correo_cli:String,
        @SerializedName("nit") var nit_cli:String,
        @SerializedName("tipo") val tipo:String = "usuario"


){

    lateinit var urlUsuario:String
    init {
         urlUsuario = Settings.url+"/cliente"
    }

    fun crearUsuario():Boolean{
        try{


        //configurando el data
        var gson = Gson()
        var data = gson.toJson(this) as String
         return Settings.enviarPorPost(data,urlUsuario)

        }
        catch (e: JSONException){
            return false
        }
    }
    fun modificarUsuario(){
        
    }
}