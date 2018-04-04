package com.ati.queenspasteleria.modelo

import android.util.Log
import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.security.Provider
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
        urlOfertas = Settings.url + "/proventa/ofertas/"
    }

    fun obtenerOfertas(idProVenta:String): ArrayList<ProVentaAProOferta>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlOfertas+idProVenta)

        //preparar la conversion a Json
        var gson = Gson()
        Log.i("result",result.toString())
        val turnsType = object : TypeToken<ArrayList<ProVentaAProOferta>>(){}.type
        try{

             return  gson.fromJson<ArrayList<ProVentaAProOferta>>(result,turnsType)
        }
        catch (e: JsonSyntaxException){
            return null
        }

    }
}