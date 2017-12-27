package com.ati.queenspasteleria.modelo

/**
 * Created by josue on 18/12/17.
 */
class PastelesPedido(
        var cantidad: Int,
        var rebanadas: Int,
         precio:Float,
         nombre:String,
         id:Int,
         descripcion: String,
         fotografia:List<String>
):PastelesVenta(
        precio,
        nombre,
        id,
        descripcion,
        fotografia
)