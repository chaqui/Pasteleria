package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar

import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.ConfiguracionUsuario
import kotlinx.android.synthetic.main.app_bar_principal.*


/**
 * A simple [Fragment] subclass.
 */
class UsuarioFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater!!.inflate(R.layout.fragment_usuario, container, false)
        var txWNickName = view.findViewById<TextView>(R.id.txWNickName)
        var txWNombreApellido = view.findViewById<TextView>(R.id.txWNombreApellido)
        var txWNit = view.findViewById<TextView>(R.id.txWNit)
        var txWDireccion = view.findViewById<TextView>(R.id.txWDireccion)
        var txWTelefono = view.findViewById<TextView>(R.id.txWTelefono)
        var btCambiarCOntrasenia = view.findViewById<Button>(R.id.btCambiarCOntrasenia)

        activity.title=""


        var configuracionUsuario = ConfiguracionUsuario()

        val usuario = configuracionUsuario.leerArhivoUsuario(context)

        txWNickName.text = usuario!!.nickname_cli
        txWNombreApellido.text = usuario!!.nombre_cli+ " "+usuario!!.apellido_cli
        txWNit.text = usuario!!.nit_cli
        txWDireccion.text = usuario!!.direccion_cli
        txWTelefono.text = usuario!!.telefono_cli

        btCambiarCOntrasenia.setOnClickListener {

        }


        return view
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        inflater!!.inflate(R.menu.toolbar_principal,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}// Required empty public constructor
