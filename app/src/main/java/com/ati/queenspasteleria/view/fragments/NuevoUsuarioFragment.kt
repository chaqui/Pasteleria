package com.ati.queenspasteleria.view.fragments


import android.app.AlertDialog
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
import com.ati.queenspasteleria.modelo.Usuario
import junit.framework.Assert


/**
 * A simple [Fragment] subclass.
 */
class NuevoUsuarioFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         var view=  inflater!!.inflate(R.layout.fragment_nuevo_usuario, container, false)

        //obteniendo cada objeto de la vista
        var txINombre = view.findViewById<TextInputEditText>(R.id.txINombre)
        var txIApellido = view.findViewById<TextInputEditText>(R.id.txIApellido)
        var txINit = view.findViewById<TextInputEditText>(R.id.txINit)
        var txIDireccion = view.findViewById<TextInputEditText>(R.id.txIDireccion)
        var txITelefono = view.findViewById<TextInputEditText>(R.id.txITelefono)
        var txICorreoElectronico = view.findViewById<TextInputEditText>(R.id.txICorreoElectronico)
        var txINickName = view.findViewById<TextInputEditText>(R.id.txINickName)
        var txIContrasenia1 = view.findViewById<TextInputEditText>(R.id.txIContrasenia1)
        var txIContrasenia2 = view.findViewById<TextInputEditText>(R.id.txIContrasenia2)
        var btCrearUsuario = view.findViewById<Button>(R.id.btCrearUsuario)

        btCrearUsuario.setOnClickListener{
            var verificar = VerificarCampos()
            try {

                //onvertimos todos los campos a variables utilizables
                //strings con verificacion si estan vacios los campos
                var nombre =verificar.verificarSiEstaVacio( txINombre.text.toString(), "nombre")
                var apellido =verificar.verificarSiEstaVacio(txIApellido.text.toString(), "apellido")
                var direccion = verificar.verificarSiEstaVacio(txIDireccion.text.toString(),"direccion")
                var nickName = verificar.verificarSiEstaVacio(txINickName.text.toString(),"nickname")
                var contrasenia1 = verificar.verificarSiEstaVacio( txIContrasenia1.text.toString(),"contraseña")
                var contrasenia2 =  verificar.verificarSiEstaVacio(txIContrasenia2.text.toString(),"contraseña repetida")
                var correoElectronico = txICorreoElectronico.text.toString()


                //enteros con verificacion si ingreso el numero de telefono
                var telefono =Integer.parseInt(verificar.verificarSiEstaVacio(txITelefono.text.toString(),
                        "telefono"))
                var nit =Integer.parseInt(
                        if(txINit.text.toString().equals("")) "0" else txINit.text.toString())

                //verificamos si las contraseñas son correctas
                if(contrasenia1.equals(contrasenia2)){

                    //creación del objeto de usuario
                    var usuario = Usuario(nombre,apellido,direccion,telefono,0,nickName,
                            correoElectronico,contrasenia1,nit)
                    usuario.crearUsuario() //enviar el objeto usuario para almacenar
                }
                else{
                    throw Exception()
                }

            }


            //excepcion si las contraseñas no son iguales
            catch (e:Exception){
                verificar.mostrarMensajeDeError("disculpe las contraseñas que ha ingresado" +
                        " no son iguales",context)

            }
            //excepcion si los campos estan vacios
            catch (e:ExcepcionCampoVacio){
                verificar.mostrarMensajeDeError("disculpe el campo "+e.campo+"se encuentra " +
                        "vacio",context)
            }

            //excepcion si en el numero de telefono no ingreso numeros
            catch (e:NumberFormatException){
                verificar.mostrarMensajeDeError("en los campos de telefono y nit  solo se " +
                        "puede" +
                        " ingresar numeros",context)
            }
        }

        return view
    }


}// Required empty public constructor
