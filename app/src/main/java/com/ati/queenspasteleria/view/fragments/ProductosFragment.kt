package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.ProductoVenta
import com.ati.queenspasteleria.view.adapter.ProductosAdapter


/**
 * A simple [Fragment] subclass.
 */
class ProductosFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_productos, container, false)
        var listProductos = view.findViewById<RecyclerView>(R.id.listProductos)
        var linearLayoutManager = LinearLayoutManager(context)
        //colocamos el recycler en forma Vertical
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        listProductos.layoutManager = linearLayoutManager

        var productosAdapter = ProductosAdapter(
                buidProductos(),
                R.layout.cardview_productos,
                activity
        )

        listProductos.adapter = productosAdapter
        return view
    }

    fun buidProductos ():ArrayList<ProductoVenta> {
        var productos: ArrayList<ProductoVenta> = ArrayList<ProductoVenta>()
        return productos
    }
}// Required empty public constructor
