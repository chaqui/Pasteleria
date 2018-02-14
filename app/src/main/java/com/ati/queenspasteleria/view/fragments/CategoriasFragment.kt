package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.Categoria
import com.ati.queenspasteleria.view.adapter.PictureAdapterCategorias


/**
 * A simple [Fragment] subclass.
 */
class CategoriasFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_categorias, container, false)
        //llamamos el recicler para controlarlo
        var listCategorias  =  view.findViewById<RecyclerView>(R.id.listCategorias)
        //creamos el configurador del recicler
        var linearLayoutManager = LinearLayoutManager(context)
        //colocamos el recycler en forma Vertical
        linearLayoutManager.orientation= LinearLayoutManager.VERTICAL
        //se lo implementamos al recycler
        listCategorias.layoutManager = linearLayoutManager

        var pictureAdapterCategorias = PictureAdapterCategorias(
                buidCategorias(),
                R.layout.cardview_categoria,
                activity
        )
        listCategorias.adapter=pictureAdapterCategorias
        return view

    }
    //aca hacemos la peticion y llenamos el cardVIew con la informacion Necesarioa
    fun buidCategorias ():ArrayList<Categoria>{
        var categoria = Categoria()
       var categorias:ArrayList<Categoria> = categoria.obtenerCategorias()!!
        return categorias
    }



}// Required empty public constructor
