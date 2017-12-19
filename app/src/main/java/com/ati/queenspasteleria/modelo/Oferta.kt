package com.ati.queenspasteleria.modelo

import java.util.*

/**
 * Created by josue on 18/12/17.
 */
data class Oferta(
        var fechaInicio: Date,
        var fechaFin: Date,
        var descuento: Double,
        var cantidadPasteles: Int,
        var estado: Boolean
)
