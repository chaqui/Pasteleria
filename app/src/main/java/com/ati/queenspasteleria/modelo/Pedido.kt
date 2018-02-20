package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by josue on 18/12/17.
 */
 class Pedido(
        @SerializedName("idpedido") var idpedido: Int,
        @SerializedName("fecha_entrega_pedido") var fecha_entrega_pedido: Date,
        @SerializedName("correo_contacto_pedido") var correo_contacto_pedido:String,
        @SerializedName("boleta_deposito_pedido") var boleta_deposito_pedido: String,
        @SerializedName("cliente_idcliente") var cliente_idcliente: Int,
        @SerializedName("idpasteles_pedido") var idpasteles_pedido:Int

){
    //constructor en blanco
    constructor():this(0,Date(),"","",0,0)

    // url para Pedido
    lateinit var urlPedido:String

    init{
        urlPedido = Settings.url+"/pedido/"
    }
    fun crearPedido(pedido:Pedido){
        var gson = Gson()
        val pedidoJson = gson.toJson(pedido,Pedido::class.java)
        Settings.enviarPorPost(pedidoJson,urlPedido)
    }
}