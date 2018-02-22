package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by josue on 18/12/17.
 */
 class Oferta(
        @SerializedName("idoferta") var idoferta: Int,
        @SerializedName("finicio_oferta") var finicio_oferta: Date,
        @SerializedName("fefin_oferta") var fefin_oferta: Date,
        @SerializedName("descuento_oferta") var descuento_oferta: Double,
        @SerializedName("cantidad_oferta") var cantidad_oferta: Int,
        @SerializedName("estado_oferta") var estado_oferta: Boolean
){
    constructor():this(0,Date(),Date(),0.0,0,false)
    //obtener oferta
    lateinit var urlOferta:String

    //obtener las ofertas
    lateinit var urlOfertas:String
    init{
        urlOferta = Settings.url+"/oferta/"
        urlOfertas = Settings.url+"/proventa/ofertas/"
    }

    fun obtenerOfertas(idCategoria:String): ArrayList<Oferta>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlOfertas+idCategoria)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<Oferta>>(){}.type

        //conversion de cadena a Json
        val productosVenta =  gson.fromJson<ArrayList<Oferta>>(result,turnsType)

        return productosVenta

    }

    fun obtenerOferta(idproducto:String):Oferta{

        //obtener el producto en String
        var result = Settings.recibirInfo(urlOferta+idproducto)

        //preparar la conversion a Json
        var gson = Gson()
        val oferta = gson.fromJson<Oferta>(result,Oferta::class.java)

        return oferta
    }
}
