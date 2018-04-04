package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.ProductoVenta
import com.ati.queenspasteleria.view.adapter.ProductosAdapter
import kotlinx.android.synthetic.main.app_bar_principal.*
import kotlinx.android.synthetic.main.fragment_productos.*


/**
 * A simple [Fragment] subclass.
 */
class ProductosFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_productos, container, false)
        var listProductos = view.findViewById<RecyclerView>(R.id.listProductos)

        var linearLayoutManager = LinearLayoutManager(context)
        //colocamos el recycler en forma Vertical
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        listProductos.layoutManager = linearLayoutManager

        val args = arguments
        val nombre = args.getString("categoria")
        activity.title= nombre

        var productosAdapter = ProductosAdapter(
                buidProductos(),
                R.layout.cardview_productos,
                activity
        )

        productosAdapter.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                var idProducto = productosAdapter.productos[listProductos.getChildPosition(p0)]
                        .idpro_venta

                var args = Bundle()
                args.putInt("id",idProducto)
                var productoFragment = ProductoFragment()
                productoFragment.arguments = args
                var transiction  = fragmentManager.beginTransaction()
                transiction.replace(R.id.contenedor_principal,productoFragment)
                transiction.addToBackStack(null)
                transiction.commit()

            }

        })


        listProductos.adapter = productosAdapter
        return view
    }

    fun buidProductos ():ArrayList<ProductoVenta> {

        //leer el id de la categoria enviada
        val args = arguments
        val id =args.getInt("id",0)

        //objeto de producto venta
        var productoVenta = ProductoVenta()
        var productos: ArrayList<ProductoVenta> = productoVenta.obtenerProductos(Integer.toString(id))!!
        return productos
    }

}// Required empty public constructor
