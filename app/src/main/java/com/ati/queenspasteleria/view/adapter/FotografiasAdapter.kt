package com.ati.queenspasteleria.view.adapter

import android.app.Activity
import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.Settings
import com.ati.queenspasteleria.modelo.FotografiaPasteles

/**
 * Created by josue on 22/02/18.
 */
class FotografiasAdapter(var fotografiaPasteles: ArrayList<FotografiaPasteles>,
                         var resource: Int,
                         var Activity:Activity):RecyclerView.Adapter<FotografiasAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        var view = LayoutInflater.from(parent!!.context).inflate(
                resource,
                parent,
                false
        )
        return FotografiasAdapter.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return fotografiaPasteles.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
       var fotografiaProducto = fotografiaPasteles.get(position)
        try{
            holder!!.iVProducto.setImageBitmap(Settings.recibirImagen(fotografiaProducto.urlFotografia))
        }
        catch (e: NullPointerException){
            holder!!.iVProducto.setImageResource(R.drawable.mensaje)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iVProducto    =  itemView.findViewById<ImageView>(R.id.iVProducto)


        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {

            setOnClickListener { listener(item) }
        }


    }

}