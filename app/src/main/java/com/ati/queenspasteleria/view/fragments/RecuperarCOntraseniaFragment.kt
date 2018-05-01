package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.modelo.UsuarioLogin

/**
 * A simple [Fragment] subclass.
 */
class RecuperarCOntraseniaFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_recuperar_contrasenia, container, false)
        var btRecuperarContasenia = view.findViewById<Button>(R.id.btRecuperarContasenia)
        var usuarioRecuperar = view.findViewById<TextInputEditText>(R.id.usuarioRecuperar)

        btRecuperarContasenia.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val usuario = usuarioRecuperar.text.toString()
                val usuarioLogin = UsuarioLogin(usuario,"")
                usuarioLogin.recuperarContrasenia()
            }

        })


        return view
    }

}// Required empty public constructor
