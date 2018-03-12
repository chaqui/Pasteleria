package com.ati.queenspasteleria.modelo

import android.util.Log
import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

/**
 * Created by josue on 27/12/17.
 */
 class Ocasion(
        @SerializedName("idocacion")var idocacion: Int,
        @SerializedName("nombre_oc")var nombre_oc: String
){
    constructor():this(0,"")
    // url para obtener ocasiobes de Proventa
    lateinit var urlOcasiones:String

    // url para obtener ocaciosn de proventa
    lateinit var urlOcasion:String

    init{
        urlOcasiones = Settings.url+"/proventa/ocaciones/"
        urlOcasion = Settings.url+"/ocacion/"
    }

    fun obtenerOcasiones(idProducto:String): ArrayList<Ocasion>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlOcasiones+idProducto)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<Ocasion>>(){}.type

        //conversion de cadena a Json
        val ocasiones =  gson.fromJson<ArrayList<Ocasion>>(result,turnsType)

        return ocasiones

    }   

    fun obtenerOcasion(idproducto:String):Ocasion{

        //obtener el producto en String
        var result = Settings.recibirInfo(urlOcasion+idproducto+"/0")
        Log.i("json",result)
        //preparar la conversion a Json
        var gson = Gson()
        var ocasion = gson.fromJson<Ocasion>(result,Ocasion::class.java)
        Log.i("nombre ocasion",ocasion.nombre_oc)
        return ocasion
    }
}