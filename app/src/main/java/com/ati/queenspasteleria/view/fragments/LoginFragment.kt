package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ati.queenspasteleria.ExcepcionCampoVacio

import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.VerificarCampos


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater!!.inflate(R.layout.fragment_login, container, false)
        var usuarioLogin = view.findViewById<TextInputEditText>(R.id.usuarioLogin)
        var contraseniaLogin = view.findViewById<TextInputEditText>(R.id.contraseniaLogin)
        var btLogin = view.findViewById<Button>(R.id.btLogin)

        btLogin.setOnClickListener{
            var verificar = VerificarCampos()
                try{
                    var usuario = verificar.verificarSiEstaVacio(usuarioLogin.text.toString(),
                            "usuario")
                    var contrasenia = verificar.verificarSiEstaVacio(contraseniaLogin.text.toString(),
                            "contraseña")
                }
                catch (e:ExcepcionCampoVacio){
                    verificar.mostrarMensajeDeError("disculpe el campo "+e.campo+"se encuentra vacio",context)
                }
        }
        return view
    }

}// Required empty public constructor
