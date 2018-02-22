package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

/**
 * Created by josue on 22/02/18.
 */

class ProVentaAProOferta(
        @SerializedName("pro_venta_idpro_venta")var pro_venta_idpro_venta:Int,
        @SerializedName("oferta_idoferta")var oferta_idoferta:Int
){
constructor():this(0,0)

    lateinit var urlOfertas:String
    init {
        urlOfertas = Settings.url + "proventa/ofertas/"
    }

    fun obtenerOfertas(idProVenta:String): ArrayList<ProVentaAProOferta>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlOfertas+idProVenta)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<ProVentaAProOferta>>(){}.type

        //conversion de cadena a Json
        val proVentaAProOfertas =  gson.fromJson<ArrayList<ProVentaAProOferta>>(result,turnsType)

        return proVentaAProOfertas

    }
}