package com.ati.queenspasteleria.view.fragments


import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.transition.Slide
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.Categoria
import com.ati.queenspasteleria.view.adapter.PictureAdapterCategorias
import kotlinx.android.synthetic.main.app_bar_principal.*
import kotlinx.android.synthetic.main.tool_bar_search.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_principal.*


/**
 * A simple [Fragment] subclass.
 */
class CategoriasFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_categorias, container, false)
        setHasOptionsMenu(true)



        /*Funciones y elementos del Toolbar
         *conectandose al tooolbar
        */
        var toolbar_search = view.findViewById<Toolbar>(R.id.tool_bar_search)

        //conectandose al ImageButton del Toolbar
        var imageButton2 = toolbar_search.findViewById<ImageButton>(R.id.imageButton2)



        //                  eventos del Toolbar

        //evento de la busqueda del toolbar
        imageButton2.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?)
            {
                var busquedaFragment = BusquedaFragment()
                var transaction = fragmentManager.beginTransaction()

               transaction.replace(R.id.contenedor_principal, busquedaFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })


        //llamamos el recicler para controlarlo
        var listCategorias  =  view.findViewById<RecyclerView>(R.id.listCategorias)
        //creamos el configurador del recicler
        var linearLayoutManager = LinearLayoutManager(context)
        //colocamos el recycler en forma Vertical
        linearLayoutManager.orientation= LinearLayoutManager.VERTICAL
        //se lo implementamos al recycler
        listCategorias.layoutManager = linearLayoutManager
        activity.title= "categorias"



        var pictureAdapterCategorias = PictureAdapterCategorias(
                buidCategorias(),
                R.layout.cardview_categoria,
                activity
        )

        pictureAdapterCategorias.setOnClickListener(object: View.OnClickListener
        {
            override fun onClick(p0: View?)
            {

                var idCategoria =pictureAdapterCategorias.
                        categorias[listCategorias.getChildPosition(p0)].id

                var nombreCategoria =pictureAdapterCategorias.
                        categorias[listCategorias.getChildPosition(p0)].cat_nombre
                var args = Bundle()
                args.putInt("id",idCategoria)
                args.putString("categoria",nombreCategoria)
                var productosFragment = ProductosFragment()
                productosFragment.arguments =args
                var transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal, productosFragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }

        })
        listCategorias.adapter=pictureAdapterCategorias


        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
       setHasOptionsMenu(true)
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        inflater!!.inflate(R.menu.principal,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //aca hacemos la peticion y llenamos el cardVIew con la informacion Necesarioa
    fun buidCategorias ():ArrayList<Categoria>
    {
        var categoria = Categoria()
       var categorias:ArrayList<Categoria> = categoria.obtenerCategorias()!!

        return categorias
    }



}// Required empty public constructor
