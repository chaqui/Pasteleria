package com.ati.queenspasteleria.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ati.queenspasteleria.R


/**
 * A simple [Fragment] subclass.
 */
class TipoDePagoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_tipo_de_pago, container, false)
    }

}// Required empty public constructor