package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by josue on 18/12/17.
 */
 class Usuario (
        @SerializedName("idCLiente")  var idCliente: Int,
        @SerializedName("nombre_cli") var nombre_cli: String,
        @SerializedName("apellido_cli") var apellido_cli: String,
        @SerializedName("direccion_cli") var direccion_cli:String,
        @SerializedName("telefono_cli") var telefono_cli:String,
        @SerializedName("nickname_cli") var nickname_cli: String,
        @SerializedName("password_cli") var password_cli: String,
        @SerializedName("correo_cli") var correo_cli:String,
        @SerializedName("nit_cli") var nit_cli:String,
        @SerializedName("tipo") val tipo:String = "usuario"


){

    lateinit var urlUsuario:String
    init {
         urlUsuario = Settings.url+"/usuario/"
    }

    fun crearUsuario():Boolean{

        //configurando el data
        var gson = Gson()
        var data = gson.toJson(this) as String
        Settings.enviarPorPost(data,urlUsuario)
        return true
    }
    fun modificarUsuario(){
        
    }
}