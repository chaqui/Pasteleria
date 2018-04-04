package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

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


    init{
        urlOferta = Settings.url+"/oferta/"
    }


    fun obtenerOferta(idproducto:String):Oferta{

        //obtener el producto en String
        var result = Settings.recibirInfo(urlOferta+idproducto)

        //preparar la conversion a Json
        var gson = Gson()
        val oferta = gson.fromJson<Oferta>(result,Oferta::class.java)

        return oferta
    }

    fun obtenerOfertas(id: Int): ArrayList<Oferta>? {
        var ofertas = ArrayList<Oferta>()
        var proVentaAProOferta = ProVentaAProOferta()
        try{
            var proVentaAProOfertas: ArrayList<ProVentaAProOferta> = proVentaAProOferta.obtenerOfertas(id.toString())!!

            for (i in proVentaAProOfertas.indices){
                ofertas.add(this.obtenerOferta(proVentaAProOfertas[i].oferta_idoferta.toString()))
            }


            return ofertas
        }
        catch (e: KotlinNullPointerException)
        {
            return null
        }

    }
}
