package com.ati.queenspasteleria.modelo

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
        val tipo:String = "usuario"
){
    fun crearUsuario(){

    }
    fun modificarUsuario(){
        
    }
}