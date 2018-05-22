package com.ati.queenspasteleria.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.support.v7.widget.Toolbar
import android.view.View
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.ConfiguracionUsuario
import com.ati.queenspasteleria.view.fragments.*
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        var toolbar = findViewById<Toolbar>(R.id.tool_bar)

        setSupportActionBar(toolbar)

        this.title="Queens Pasteleria"


        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedor_principal,CategoriasFragment())
        transaction.addToBackStack(null)
        transaction.commit()
        nav_view.setNavigationItemSelectedListener(this)


        //evento del textView de inciar Sesion
        var navigationView = findViewById<NavigationView>(R.id.nav_view)
        var headerView = navigationView.getHeaderView(0)
        var txVInicieSesion = headerView.findViewById<TextView>(R.id.txVInicieSesion)

        var menu = navigationView.menu

        var itemLogin = menu.findItem(R.id.login)



        var configuracionUsuario = ConfiguracionUsuario()

        if (configuracionUsuario.verificarUsuarioInicioSesion(this )){
            val usuario = configuracionUsuario.leerArhivoUsuario(this)
            if (usuario != null) {
                txVInicieSesion.text = usuario.nickname_cli
                itemLogin.title = "Salir"
            }
        }

        txVInicieSesion.setOnClickListener({
            drawer_layout.closeDrawer(GravityCompat.START)

            //si no esta loggeado abrir fragment logib
            if(!configuracionUsuario.verificarUsuarioInicioSesion(this)){
                //toolbar.title="Iniciar Sesion"
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,LoginFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
            //si esta loggeado abrir el fragment de detalles de usuario
            else
            {
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,UsuarioFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })

        if(configuracionUsuario.verificarUsuarioInicioSesion(this)){

                }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.busquedaPasteles->{
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,BusquedaFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return  true
            }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        drawer_layout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.Pasteles ->{
                Log.i("when","Productos")
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,CategoriasFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true}
            R.id.Pedidos -> {
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,ProductosFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
            R.id.usuario ->{
                Log.i("when","Usuario")
                var configuracionUsuario = ConfiguracionUsuario()
                if(!configuracionUsuario.verificarUsuarioInicioSesion(this)){
                    //toolbar_title.text= "Iniciar Sesion"
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedor_principal,LoginFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return true
                }
                else{
                    //toolbar_title.text= "Usuario"
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedor_principal,UsuarioFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return  true
                }

                return true
            }
            R.id.login->{
                var configuracionUsuario = ConfiguracionUsuario()
                if(!configuracionUsuario.verificarUsuarioInicioSesion(this)){
                    //toolbar_title.text= "Iniciar Sesion"
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedor_principal,LoginFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return true
                }
                else{
                    preguntarPorSalir(this)
                    return  true
                }
                return true
            }


            else -> return super.onOptionsItemSelected(item)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun preguntarPorSalir(context:Context){
        var mensajeDeAlerta = AlertDialog.Builder(context)
        mensajeDeAlerta.setTitle("alerta!")
        mensajeDeAlerta.setMessage("¿Quiere Cerrar Sesion?")
        var transaction = supportFragmentManager.beginTransaction()
        mensajeDeAlerta.setPositiveButton("SI", {dialogInterface: DialogInterface, i: Int ->
            val configuracionDeUsuario = ConfiguracionUsuario()
            configuracionDeUsuario.eliminarRegistroUsuario(this)
            transaction.replace(R.id.contenedor_principal,ProductosFragment())
                    .addToBackStack(null).commit()
        })
        mensajeDeAlerta.setNegativeButton("no",{dialogInterface: DialogInterface, i: Int ->
            transaction.replace(R.id.contenedor_principal,ProductosFragment())
                    .addToBackStack(null).commit()
        })
        mensajeDeAlerta.show()
    }

}
