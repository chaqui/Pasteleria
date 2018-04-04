package com.ati.queenspasteleria.modelo

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.widget.ImageView
import com.ati.queenspasteleria.Settings.Settings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

/**
 * Created by josue on 18/12/17.
 */
open class  ProductoVenta(

        //Serializar Variables
        @SerializedName("idpro_venta") var idpro_venta:Int,
        @SerializedName("nombre_pro_venta") var nombre_pro_venta:String,
        @SerializedName("precio_pro_venta") var precio_pro_venta:Float,
        @SerializedName("descripcion_pro_venta") var descripcion_pro_venta: String,
        @SerializedName("decoracion_pastel") var decoracion_pastel:String,
        @SerializedName("categoria_id") var categoria_id: Int,
        @SerializedName("ocacion_id") var ocacion_id: Int
)
{
    //constructor en blanco
    constructor():this(0,"",0f,"","",0,0)


    // url para obtener productos de categoria
    lateinit var urlProductos:String

    // url para obtener detalles de producto
    lateinit var urlProducto:String

    //def
    init {
        urlProductos = Settings.url+"/categoria/proventas/"
        urlProducto = Settings.url+"/proventa/"
    }

    //funciones para obtener los productos por medio de un id de Categoria
    fun obtenerProductos(idCategoria:String): ArrayList<ProductoVenta>? {


        //obtener los productos en String
        var result = Settings.recibirInfo(urlProductos+idCategoria)

        //preparar la conversion a Json
        var gson = Gson()
        val turnsType = object : TypeToken<ArrayList<ProductoVenta>>(){}.type

        //conversion de cadena a Json
        val productosVenta =  gson.fromJson<ArrayList<ProductoVenta>>(result,turnsType)

        return productosVenta

    }

    fun obtenerProducto(idproducto:String):ProductoVenta{

        //obtener el producto en String
        var result = Settings.recibirInfo(urlProducto+idproducto)

        //preparar la conversion a Json
            var gson = Gson()
            val productoVenta = gson.fromJson<ProductoVenta>(result,ProductoVenta::class.java)

        return productoVenta
    }

    fun obtenerImagen(nombreImagen: String): Bitmap? {
        var bitmap = Settings.recibirImagen("")




        return bitmap
    }


}