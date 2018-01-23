package com.ati.queenspasteleria.modelo

/**
 * Created by josue on 18/12/17.
 */
open class  ProductoVenta(
        var precio:Float,
        var nombre:String,
        var id:Int,
        var descripcion: String,
        var fotografia:List<String>,
        var ocasiones: List<Ocasion>
)