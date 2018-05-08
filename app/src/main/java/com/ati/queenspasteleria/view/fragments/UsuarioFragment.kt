package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.ConfiguracionUsuario


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

}// Required empty public constructor
