package com.ati.queenspasteleria.modelo


import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by josue on 18/12/17.
 */
class DetallesPedido(
        @SerializedName("id_dett_pedido") var id_dett_pedido: Int,
        @SerializedName("cantidad_pasteles_pedido") var cantidad_pasteles_pedido: Int,
        @SerializedName("rebanadas_pasteles_pedido") var rebanadas_pasteles_pedido: Int,
        @SerializedName("detalles_prod_pedido") var detalles_prod_pedido:String,
        @SerializedName("foto_decoracion") var foto_decoracion: String,
        @SerializedName("descripcion_pastel") var descripcion_pastel: String,
        @SerializedName("pasteles_venta_id") var pasteles_venta_id:Int

)
{
    //constructor en blanco
    constructor():this(0,0,0,"","","",0)
    // url para  productos pedidos
    lateinit var urlDetallesPedido:String


    init{
        urlDetallesPedido =  Settings.url+"/detallepedido/"

    }

    fun crearDetallesPedido( detallesPedido: DetallesPedido)
    {
        var gson = Gson()
        val pedidoJson = gson.toJson(detallesPedido,DetallesPedido::class.java)
        Settings.enviarPorPost(pedidoJson,urlDetallesPedido)
    }

}