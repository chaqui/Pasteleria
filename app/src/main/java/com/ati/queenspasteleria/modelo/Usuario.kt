package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by josue on 18/12/17.
 */
 class Usuario (
        var idCliente: Int,
        var nombre_cli: String,
        var apellido_cli: String,
        var direccion_cli:String,
        var telefono_cli:String,
        var nickname_cli: String,
        var password_cli: String,
        var correo_cli:String,
        var nit_cli:String,
        val tipo:String = "usuario"


){

    lateinit var urlUsuario:String
    init {
         urlUsuario = Settings.url+"/usuario"
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