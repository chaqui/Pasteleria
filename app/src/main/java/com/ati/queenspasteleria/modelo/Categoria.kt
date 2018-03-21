package com.ati.queenspasteleria.modelo

import android.util.Log
import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

/**
 * Created by josue on 22/01/18.
 */
class Categoria(
        @SerializedName("idcategoria") var id: Int,
        @SerializedName("cat_nombre") var cat_nombre:String
)
{
     constructor():this(0,"0")

    lateinit var urlCategoria:String
    init {
        urlCategoria = Settings.url+"/categoria/"
    }

    fun obtenerCategorias(): ArrayList<Categoria>? {
        //obtener las categorias en String
        var result = Settings.recibirInfo(urlCategoria)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<Categoria>>(){}.type

        //conversion de cadena a Json
        val categorias =  gson.fromJson<ArrayList<Categoria>>(result,turnsType)

        return categorias

    }


}
