package com.ati.queenspasteleria.view.viewHolder

import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ati.queenspasteleria.R

/**
 * Created by josue on 19/01/18.
 */


    class ViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        val txWTitulo   =  itemView.findViewById(R.id.txWTitulo) as TextView
        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {


            setOnClickListener { listener(item) }
        }

}