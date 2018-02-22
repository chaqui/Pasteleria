package com.ati.queenspasteleria.modelo

import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

/**
 * Created by josue on 21/02/18.
 */
class FotografiaPasteles(
        @SerializedName("idfoto_pasteles") var idfoto_pasteles:Int,
        @SerializedName("url_foto") var url_foto: String,
        @SerializedName("pro_ventaid") var pro_ventaid: Int,
        var ubicacionFotografia: String

)
{
    constructor() :this(0,"",0,"")

    //obtener oferta
    lateinit var urlFotografia:String

    //obtener las ofertas
    lateinit var urlFotografias:String

    init {
        urlFotografia = Settings.url + "/fotografia/"
        urlFotografias = Settings.url + "/proventa/fotografias/"
    }

    fun obtenerFotografias(idCategoria:String): ArrayList<FotografiaPasteles>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlFotografias+idCategoria)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<FotografiaPasteles>>(){}.type

        //conversion de cadena a Json
        val productosVenta =  gson.fromJson<ArrayList<FotografiaPasteles>>(result,turnsType)

        return productosVenta

    }

    fun obtenerFotografia(idproducto:String):FotografiaPasteles{

        //obtener el producto en String
        var result = Settings.recibirInfo(urlFotografia+idproducto)

        //preparar la conversion a Json
        var gson = Gson()
        val fotografia = gson.fromJson<FotografiaPasteles>(result,FotografiaPasteles::class.java)

        return fotografia
    }

    fun descargarFOtografia(){

    }

}