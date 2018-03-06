package com.ati.queenspasteleria.view.adapter

import android.app.Activity
import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.util.Log
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
                                var activity: Activity) :RecyclerView.Adapter<PictureAdapterCategorias.ViewHolder>(),View.OnClickListener {

    private lateinit var listener:View.OnClickListener


    override fun onClick(p0: View?) {
        if(listener != null)
            listener.onClick(p0)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var categoria = categorias.get(position)
        holder!!.txWTitulo.text = categoria.cat_nombre

    }

    override fun getItemCount(): Int {
        Log.i("cantidad de Categorias",categorias.size.toString())
        return categorias.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(
                resource,
                parent,
                false
        )
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txWTitulo    =  itemView.findViewById<TextView>(R.id.txWTitulo)

        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {


            setOnClickListener { listener(item) }
        }


    }
}

