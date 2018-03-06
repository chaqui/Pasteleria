package com.ati.queenspasteleria.excepciones

/**
 * Created by josue on 5/03/18.
 */
class  ExcepcionTamanioCadena: Exception(){
    //la variable campo es para colocar el nombre del elemento que no a ingresado ni un dato
    lateinit var campo:String
}