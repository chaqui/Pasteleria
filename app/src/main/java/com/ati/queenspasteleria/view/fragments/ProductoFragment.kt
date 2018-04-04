package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.Settings
import com.ati.queenspasteleria.modelo.FotografiaPasteles
import com.ati.queenspasteleria.modelo.Ocasion
import com.ati.queenspasteleria.modelo.Oferta
import com.ati.queenspasteleria.modelo.ProductoVenta
import com.ati.queenspasteleria.view.adapter.FotografiasAdapter
import com.ati.queenspasteleria.view.adapter.OfertasAdapter
import kotlinx.android.synthetic.main.fragment_productos.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProductoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductoFragment : Fragment() {




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater!!.inflate(R.layout.fragment_producto, container, false)

        //obteniendo el id del Producto
        val args = arguments
        val id =args.getInt("id",0)

        //obtener datos del producto
        var productoVenta = ProductoVenta().obtenerProducto(id.toString())


        //conectar a objetos del Fragment
        var imageView2 = view.findViewById<ImageView>(R.id.imageView2)
        var txNombreProducto = view.findViewById<TextView>(R.id.txNombreProducto)
        var txVOcaciones = view.findViewById<TextView>(R.id.txVOcaciones)
        var txVDescripcion = view.findViewById<TextView>(R.id.txVDescripcion)
        var txVPrecio = view.findViewById<TextView>(R.id.txVPrecio)
        var listOfertas = view.findViewById<RecyclerView>(R.id.listOfertas)
        var listFotografias = view.findViewById<RecyclerView>(R.id.listFotografias)


        //                   mostrar informacion  del producto
        //obtener Fotografia
        var fotografiaPasteles = buidFotografias(productoVenta.idpro_venta)

        //mostrar imagen de cabecera
        imageView2.setImageBitmap(Settings.recibirImagen(fotografiaPasteles[0].urlFotografia))

       //nombre del producto
        txNombreProducto.text = productoVenta.nombre_pro_venta
        //ocasion
        txVOcaciones.text =  Ocasion().obtenerOcasion(productoVenta.ocacion_id.toString()).nombre_oc
        //descripcion
        txVDescripcion.text = productoVenta.descripcion_pro_venta
        //Precio
        txVPrecio.text = "Q. "+productoVenta.precio_pro_venta

        activity.title = productoVenta.nombre_pro_venta



        //                              Recycler Views


        //Recycler View de Ofertas
        var ofertas = buidOfertas(productoVenta.idpro_venta)
        if(ofertas!=null)
        {
            var linearLayoutManager = LinearLayoutManager(context)

            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

            var ofertasAdapter = OfertasAdapter(ofertas,
                    R.layout.cardview_ofertas,
                    activity)

            listOfertas.layoutManager = linearLayoutManager
            listOfertas.adapter = ofertasAdapter

        }

        //Recycler View de Fotografias
        /*if(fotografiaPasteles!= null){
            var linearLayoutManagerh = LinearLayoutManager(context)

            linearLayoutManagerh.orientation = LinearLayoutManager.HORIZONTAL

            var fotografiasAdapter = FotografiasAdapter(fotografiaPasteles,
                    R.layout.cardview_ofertas,
                    activity)

            listFotografias.layoutManager = linearLayoutManagerh
            listFotografias.adapter = fotografiasAdapter
        }
          */



        return view
    }

    fun buidOfertas(id:Int): ArrayList<Oferta>? {
        try {
            var oferta = Oferta()
            var ofertas:ArrayList<Oferta> = oferta.obtenerOfertas(id)!!
            return ofertas
        }
        catch (e:KotlinNullPointerException )
        {
            return null
        }

    }

    fun buidFotografias(id:Int):ArrayList<FotografiaPasteles>{
        var fotografiaPasteles = FotografiaPasteles()
        var fotografiasPasteles:ArrayList<FotografiaPasteles> = fotografiaPasteles.obtenerFotografias(id.toString())!!
        return fotografiasPasteles
    }


}// Required empty public constructor
