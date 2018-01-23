package com.ati.queenspasteleria.view.adapter

import android.app.Activity
import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.ProductoVenta

/**
 * Created by josue on 22/01/18.
 */
class ProductosAdapter(var productos:ArrayList<ProductoVenta>,
                       var resource:Int,
                       var activity:Activity

):RecyclerView.Adapter<ProductosAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
       var producto = productos.get(position)
        holder!!.iVProducto = producto.fotografia.get(0) as ImageView
        holder!!.txWNombre.text = producto.nombre

        //conctenando todas las ocasiones
        var ocasiones: String =""
        for(ocasion in producto.ocasiones)
        {
            ocasiones + ocasion.nombre+", "
        }
        ocasiones=  ocasiones.dropLast(2)


        holder!!.txWOcasion.text = ocasiones
        holder!!.txWDescripcion.text = producto.descripcion

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        var view = LayoutInflater.from(parent!!.context).inflate(
                resource,
                parent,
                false
        )
        return ProductosAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productos.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var iVProducto    =  itemView.findViewById<ImageView>(R.id.iVProducto)
        var txWNombre    =  itemView.findViewById<TextView>(R.id.txWNombre)
        var txWOcasion    =  itemView.findViewById<TextView>(R.id.txWOcasion)
        var txWDescripcion    =  itemView.findViewById<TextView>(R.id.txWNombre)
        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {


            setOnClickListener { listener(item) }
        }
    }

}