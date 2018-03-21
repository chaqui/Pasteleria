package com.ati.queenspasteleria.Settings

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ati.queenspasteleria.modelo.Usuario
import com.thoughtworks.xstream.XStream
import java.io.*
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Created by josue on 24/01/18.
 */
class ConfiguracionUsuario(){

    //variable que incluye el nombre de archivo: usuario.xml
    private var nombreArchivo ="usuario.xml"

    //variable para convertir Objeto a Xml
    private var xStream = XStream()

    //constructor de la clase
    init {

    }
    //verificacion si el archivo usuario.xml existe
    fun verificarUsuarioInicioSesion():Boolean{
        var archivo = File(nombreArchivo)
        return  archivo.exists()
        }

    //crear el archivo usuario.xml
    fun crearArchivoUsuario(usuario:Usuario, context: Context){
        //configuracion del convertidor de XML
        xStream.alias("usuario",Usuario::class.java)
        //iniciamos el mwanejo de excepciones
        try{

            //configuramos el conversor de Xml
            var stringXml = xStream.toXML(usuario)
            if(leerArhivoUsuario(context) == null){
                var fout = OutputStreamWriter( context.openFileOutput("usuario.xml", Context.MODE_PRIVATE))
                fout.write(stringXml)
                fout.close()
                Log.i("Ficheros","fichero Creado")
            }


        }

        catch (e:Exception){
            //enviar un mensaje de error de que no se pudo escribir el archivo
            Log.i("Ficheros","fichero no Creado")
           var toast = Toast.makeText(context,"Error en crear usuario",Toast.LENGTH_SHORT)
            toast.show()
        }
    }


    fun leerArhivoUsuario(context:Context): Usuario? {
        //configuracion del convertidor de XML
        xStream.alias("usuario",Usuario::class.java)

        try{
            var fin = BufferedReader(InputStreamReader(context.openFileInput("usuario.xml")))
            var texto =""
            var linea:String? = null

            while ({linea = fin.readLine(); linea}() != null){
                texto = linea+"\n"
            }
            fin.close()
            var usuario = xStream.fromXML(texto) as Usuario

            return usuario

        }

        catch(e:Exception){
            //enviar un mensaje de error de que no se pudo escribir el archivo
            var toast = Toast.makeText(context,"Error en establecer usuario",Toast.LENGTH_SHORT)
            toast.show()
            return null
        }

    }

    //eliminar todo registro del usuario

    fun eliminarRegistroUsuario(context:Context){

        //vreamos la variable del archivo
        var archivo = File(nombreArchivo)

        //iniciamos el manejo de excepciones
        try{
            context.deleteFile("usuario.xml") //eliminamos el archivo
        }

        catch (e:Exception){
            //enviar un mensaje de error de que no se pudo eliminar el archivo
            var toast = Toast.makeText(context,"Error en eliminar usuario",Toast.LENGTH_SHORT)
            toast.show()

        }
    }



}