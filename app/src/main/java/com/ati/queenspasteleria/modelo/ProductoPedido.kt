package com.ati.queenspasteleria.modelo

/**
 * Created by josue on 18/12/17.
 */
class ProductoPedido(
        var cantidad: Int,
        var rebanadas: Int,
         precio:Float,
         nombre:String,
         id:Int,
         descripcion: String,
         fotografia:List<String>,
        ocasion:List<Ocasion>
):ProductoVenta(
        precio,
        nombre,
        id,
        descripcion,
        fotografia,
        ocasion
)