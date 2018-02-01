package com.ati.queenspasteleria.Settings

import android.content.Context
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
        //iniciamos el manejo de excepciones
        try{

            //variables para administrar los archivos
            var archivo = File(nombreArchivo)
            var bw: BufferedWriter
            //configuramos el conversor de Xml

            var stringXml = xStream.toXML(usuario)

            //verificamos si existe el archivo
            if (archivo.exists()){
                throw Exception("el archivo existe")  //si existe lo enviamos a excepcion
            }

            else{

                //si no existe creamos el archivo
                bw = BufferedWriter(FileWriter(archivo)) //preparar el buffer
                bw.write(stringXml) //escribir el archivo
                bw.close()          //cerramos el archivo
            }


        }

        catch (e:Exception){
            //enviar un mensaje de error de que no se pudo escribir el archivo
           var toast = Toast.makeText(context,"Error en crear usuario",Toast.LENGTH_SHORT)
            toast.show()
        }
    }


    fun leerArhivoUsuario(context:Context): Usuario? {
        //configuracion del convertidor de XML
        xStream.processAnnotations(Usuario::class.java)
        //iniciamos el manejo de excepciones
        try {
            //variable de la cadena que tieen el archivo
            var cadenaRecibida: String =""
            //variables para administrar los archivos
            var archivo = FileReader(nombreArchivo)
            var br = BufferedReader(archivo)

            //leemos el contenido del archivo
            var linea:String
            while((  br.readLine())!=null) {
                    linea = br.readLine()
                    cadenaRecibida  =cadenaRecibida + linea //concatenamos cada linea
            }

            //convertimos la cadena en objeto Usuario
            var usuario: Usuario = xStream.fromXML(cadenaRecibida) as Usuario

            return  usuario //retornamos el objeto Usuario
        }

        catch(e:Exception){
            //enviar un mensaje de error de que no se pudo escribir el archivo
            var toast = Toast.makeText(context,"Error en crear usuario",Toast.LENGTH_SHORT)
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
            archivo.delete() //eliminamos el archivo
        }

        catch (e:Exception){
            //enviar un mensaje de error de que no se pudo eliminar el archivo
            var toast = Toast.makeText(context,"Error en eliminar usuario",Toast.LENGTH_SHORT)
            toast.show()

        }
    }



}