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
import android.view.Menu
import android.view.MenuItem
import com.ati.queenspasteleria.R
import com.ati.queenspasteleria.Settings.ConfiguracionUsuario
import com.ati.queenspasteleria.view.fragments.LoginFragment
import com.ati.queenspasteleria.view.fragments.ProductosFragment
import com.ati.queenspasteleria.view.fragments.UsuarioFragment
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*

class Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var transaction = supportFragmentManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

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
                toolbar.title="Productos"
                transaction.replace(R.id.fragmentPrincipal,ProductosFragment())
                transaction.commit()
                return true}
            R.id.Pedidos -> {
                toolbar.title="Pedidos"
                transaction.replace(R.id.fragmentPrincipal,ProductosFragment())
                transaction.commit()
                return true
            }
            R.id.usuario ->{
                toolbar.title="Usuario"
                transaction.replace(R.id.fragmentPrincipal,UsuarioFragment())
                transaction.commit()
                return true
            }
            R.id.login->{
                var configuracionUsuario = ConfiguracionUsuario()
                if(!configuracionUsuario.verificarUsuarioInicioSesion()){
                    toolbar.title="Iniciar Sesion"
                    return true
                    transaction.replace(R.id.fragmentPrincipal,LoginFragment())
                    transaction.commit()
                }
                else{
                    preguntarPorSalir(this)
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun preguntarPorSalir(context:Context){
        val mensajeDeAlerta = AlertDialog.Builder(context).create()
        mensajeDeAlerta.setTitle("alerta!")
        mensajeDeAlerta.setMessage("¿Quiere Cerrar Sesion?")
        mensajeDeAlerta.setButton(AlertDialog.BUTTON_POSITIVE,"si",{
            dialogInterface, i ->
            val configuracionDeUsuario = ConfiguracionUsuario()
            configuracionDeUsuario.eliminarRegistroUsario()
            transaction.replace(R.id.fragmentPrincipal,ProductosFragment()).commit()
            
        })
        mensajeDeAlerta.setButton(AlertDialog.BUTTON_NEGATIVE,"no",{
            dialogInterface, i ->
            transaction.replace(R.id.fragmentPrincipal,ProductosFragment()).commit()

        })
        
    }

}
