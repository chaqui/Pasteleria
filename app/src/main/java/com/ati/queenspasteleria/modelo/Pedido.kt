package com.ati.queenspasteleria.modelo

import java.util.*

/**
 * Created by josue on 18/12/17.
 */
 class Pedido(
        var fechaDeCracion: Date,
        var fechaDeEntrega:Date,
        var nombreDePersonaDelPedido: String,
        var telefonoContacto: String,
        var correoContacto:String,
        var boletaDeposito:String,
        var ProductoPedido:List<ProductoPedido>
){
    fun crearPedido(){

    }
}