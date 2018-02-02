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
        var nombre: String,
        var apellido: String,
        var direccion:String,
        var telefono:Int,
        var id:Int,
        var nickname: String,
        var contrasenia: String,
        var correoElectronico:String,
        var nit:Int,
        val tipo:String = "usuario"


){

    lateinit var urlUsuario:String
    init {
         urlUsuario = Settings.url+"/usuario"
    }

    fun crearUsuario(){

        //configurando el data
        var gson = Gson()
        var data = gson.toJson(this) as String
        Settings.enviarPorPost(data,urlUsuario)
    }
    fun modificarUsuario(){
        
    }
}