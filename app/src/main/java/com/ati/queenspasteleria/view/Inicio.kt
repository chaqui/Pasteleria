package com.ati.queenspasteleria.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.Toast
import android.content.Intent
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.Settings
import java.net.InetAddress


class Inicio : AppCompatActivity() {

    private val DURACION_SPLASH:Long = 3000  //tiempo que se muestra el splash}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //timer par el Splash
        Handler().postDelayed(Runnable {
            fun run(){}
                //verificar si hay conexion a Internet
               try{


                //Si hay inicia el activity principal
                if(verificarConexionAInternet(this)){
                    if (verificarConexionAServidor())
                    {
                        iniciarActivity()
                    }
                    else
                    {
                        enviarMensajeSinConexion(this,"no hay conexion con el servidor")
                        finish()
                    }

                }

                // si no hay conexion envia un mensaje y sale de la conexion
                else{
                    enviarMensajeSinConexion(this,"no hay conexion a Internet")
                    finish()

                }
                }
               catch (e:Exception){
                   finish()
               }

        },DURACION_SPLASH)
    }


    //imprimir mensaje cuando no hay conexión
    private fun enviarMensajeSinConexion(context: Context, mensaje:String){
        var toast:Toast = Toast.makeText(context,mensaje,Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()

    }

    //Iniciamos Activity principal
    private fun iniciarActivity(){
        val intent = Intent(this, Principal::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }

    //funcion para verificar si hay conexion a Internet
    private fun verificarConexionAInternet(context: Context):Boolean{
        var connectivityManager: ConnectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var infoWifi: NetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        var infoDatos: NetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        // si hay conexion retorna TRUE si no retorna FALSE
        if(infoWifi.isConnected || infoDatos.isConnected ) return true
        return false
    }

    private fun verificarConexionAServidor():Boolean{
        val timeOut = 5000
        var connect:Boolean = true
        try{
            var innet = InetAddress.getByName(Settings.url)
            connect = innet.isReachable(timeOut)

        }
        catch(e:Exception){
            connect=false
        }
        finally {
            return connect
        }

    }
}
