package com.ati.queenspasteleria.view

import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.ConfiguracionUsuario
import com.ati.queenspasteleria.view.fragments.CategoriasFragment
import com.ati.queenspasteleria.view.fragments.LoginFragment
import com.ati.queenspasteleria.view.fragments.ProductosFragment
import com.ati.queenspasteleria.view.fragments.UsuarioFragment
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*

class Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedor_principal,CategoriasFragment())
        transaction.addToBackStack(null)
        transaction.commit()
        nav_view.setNavigationItemSelectedListener(this)
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
        menuInflater.inflate(R.menu.principal, menu)
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {

        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.Pasteles ->{
                Log.i("when","Productos")
                toolbar.title="Productos"
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,CategoriasFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true}
            R.id.Pedidos -> {
                Log.i("when","Pedidos")
                toolbar.title="Pedidos"
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,ProductosFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
            R.id.usuario ->{
                Log.i("when","Usuario")
                toolbar.title="Usuario"
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_principal,UsuarioFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
            R.id.login->{
                Log.i("when","login")
                var configuracionUsuario = ConfiguracionUsuario()
                if(!configuracionUsuario.verificarUsuarioInicioSesion()){
                    toolbar.title="Iniciar Sesion"
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
        var mensajeDeAlerta = AlertDialog.Builder(context).create()
        mensajeDeAlerta.setTitle("alerta!")
        mensajeDeAlerta.setMessage("¿Quiere Cerrar Sesion?")
        var transaction = supportFragmentManager.beginTransaction()
        mensajeDeAlerta.setButton(AlertDialog.BUTTON_POSITIVE,"si",{

            dialogInterface, i ->
            val configuracionDeUsuario = ConfiguracionUsuario()
            configuracionDeUsuario.eliminarRegistroUsuario(this)
            transaction.replace(R.id.contenedor_principal,ProductosFragment())
                    .addToBackStack(null).commit()
            
        })
        mensajeDeAlerta.setButton(AlertDialog.BUTTON_NEGATIVE,"no",{
            dialogInterface, i ->
            transaction.replace(R.id.contenedor_principal,ProductosFragment())
                    .addToBackStack(null).commit()

        })
        
    }

}
