package com.ati.queenspasteleria.Settings

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ati.queenspasteleria.modelo.Usuario
import com.google.gson.Gson
import com.thoughtworks.xstream.XStream
import java.io.*
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Created by josue on 24/01/18.
 */
class ConfiguracionUsuario(){

    //variable que incluye el nombre de archivo: usuario.xml
    private var nombreArchivo ="usuario.json"

    //variable para convertir Objeto a Xml
    private var xStream = XStream()

    //constructor de la clase
    init {

    }
    //verificacion si el archivo usuario.xml existe
    fun verificarUsuarioInicioSesion(context: Context):Boolean{
        try{
            var file  = context.openFileInput(nombreArchivo)
            return  true
        }
        catch (e:Exception)
        {
            return false
        }

        }

    //crear el archivo usuario.xml
    fun crearArchivoUsuario(usuario:String, context: Context){
        //configuracion del convertidor de XML

        try{
            if(verificarUsuarioInicioSesion(context)){
                eliminarRegistroUsuario(context)
            }
            var file =OutputStreamWriter (context.openFileOutput (nombreArchivo, Context.MODE_PRIVATE))
            file.write(usuario)
            file.flush()
            file.close()


        }

        catch (e:Exception){
            //enviar un mensaje de error de que no se pudo escribir el archivo
            Log.i("Ficheros","fichero no Creado "+e.toString())
           var toast = Toast.makeText(context,"Error en crear usuario",Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun crearArchivoUsuario(usuario:Usuario, context: Context){
        crearArchivoUsuario(usuario.usuarioAJson(),context)

        }




    fun leerArhivoUsuario(context:Context): Usuario? {
        //configuracion del convertidor de json
        var gson = Gson()

        try{
            var fin = BufferedReader(InputStreamReader(context.openFileInput(nombreArchivo)))
            var texto =""
            var linea:String? = null

            while ({linea = fin.readLine(); linea}() != null){
                texto = texto + linea+"\n"
            }
            fin.close()
            val usuario = gson.fromJson<Usuario>(texto, Usuario::class.java)

            return usuario

        }

        catch(e:Exception){
            //enviar un mensaje de error de que no se pudo leer el archivo
            Log.e("error de lectura",e.toString())
            var toast = Toast.makeText(context,"Error en establecer usuario",Toast.LENGTH_SHORT)
            toast.show()
            return null
        }

    }



    //eliminar todo registro del usuario

    fun eliminarRegistroUsuario(context:Context){

        //iniciamos el manejo de excepciones
        try{
            context.deleteFile(nombreArchivo) //eliminamos el archivo
            Log.i("eleminar","archivo eliminado")
        }

        catch (e:Exception){
            //enviar un mensaje de error de que no se pudo eliminar el archivo

            var toast = Toast.makeText(context,"Error en eliminar usuario",Toast.LENGTH_SHORT)
            toast.show()

        }
    }



}