package com.ati.queenspasteleria.view.adapter

import android.app.Activity
import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.Oferta
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by josue on 13/03/18.
 */
class OfertasAdapter(var ofertas: ArrayList<Oferta>,
                     var resource: Int,
                     var activity: Activity)
    :RecyclerView.Adapter<OfertasAdapter.ViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(
                resource,
                parent,
                false
        )
        return OfertasAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ofertas.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            var oferta = ofertas.get(position)
            holder!!.txOferta.text = oferta.descuento_oferta.toString()+"% de descuento"
            holder!!.txtFechaInicio.text = DateUtils.toSimpleString(oferta.finicio_oferta)
            holder!!.txVFechaFinalizacion.text = DateUtils.toSimpleString(oferta.fefin_oferta)
            holder!!.txVCantidad.text = oferta.cantidad_oferta.toString()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var txOferta = itemView.findViewById<TextView>(R.id.txOferta)
        var txtFechaInicio = itemView.findViewById<TextView>(R.id.txtFechaInicio)
        var txVFechaFinalizacion = itemView.findViewById<TextView>(R.id.txVFechaFinalizacion)
        var txVCantidad = itemView.findViewById<TextView>(R.id.txVCantidad)


        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {



        }
        }

    object DateUtils {
        @JvmStatic
        fun toSimpleString(date: Date) : String {
            val format = SimpleDateFormat("dd/MM/yyy")
            return format.format(date)
        }
    }
    }
