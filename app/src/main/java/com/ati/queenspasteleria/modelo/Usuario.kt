package com.ati.queenspasteleria.modelo

/**
 * Created by josue on 18/12/17.
 */
data class Usuario (
        var nombre: String,
        var apellido: String,
        var direccion:String,
        var telefono:int,
        var id:int,
        var nickname: String,
        var contrasenia: String
)