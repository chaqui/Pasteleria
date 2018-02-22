package com.ati.queenspasteleria.modelo

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

    fun obtenerOcasiones(idCategoria:String): ArrayList<Ocasion>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlOcasiones+idCategoria)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<Ocasion>>(){}.type

        //conversion de cadena a Json
        val ocasiones =  gson.fromJson<ArrayList<Ocasion>>(result,turnsType)

        return ocasiones

    }   

    fun obtenerOcasion(idproducto:String):Ocasion{

        //obtener el producto en String
        var result = Settings.recibirInfo(urlOcasion+idproducto)

        //preparar la conversion a Json
        var gson = Gson()
        val ocasion = gson.fromJson<Ocasion>(result,ProductoVenta::class.java)

        return ocasion
    }
}