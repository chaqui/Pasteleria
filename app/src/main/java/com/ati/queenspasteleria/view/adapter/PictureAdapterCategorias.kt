package com.ati.queenspasteleria.view.adapter

import android.app.Activity
import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.Categoria

/**
 * Created by josue on 19/01/18.
 */
class PictureAdapterCategorias( var categorias: ArrayList<Categoria>,
                                var resource:Int,
                                var activity: Activity) :RecyclerView.Adapter<PictureAdapterCategorias.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var categoria = categorias.get(position)
        holder!!.txWTitulo.text = categoria.nombre

    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return categorias.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(
                resource,
                parent,
                false
        )
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txWTitulo    =  itemView.findViewById<TextView>(R.id.txWTitulo)
        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {


            setOnClickListener { listener(item) }
        }


    }
}

