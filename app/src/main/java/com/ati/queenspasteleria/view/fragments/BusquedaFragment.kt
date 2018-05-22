package com.ati.queenspasteleria.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import com.ati.queenspasteleria.R
import kotlinx.android.synthetic.main.nav_header_principal.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BusquedaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BusquedaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusquedaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_busqueda, container, false)
        activity.title =""
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        inflater!!.inflate(R.menu.toolbar_busqueda,menu)

        var searchItem = menu!!.findItem(R.id.menu_search)
        var searchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.queryHint = getText(R.string.search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(activity,"",Toast.LENGTH_SHORT)
                searchView.setQuery("",false)
                searchView.isIconified=true

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                textView.text= p0

                return true
            }
        })


        super.onCreateOptionsMenu(menu, inflater)
    }


}// Required empty public constructor
