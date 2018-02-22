package com.ati.queenspasteleria.Settings

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.AsyncTask

/**
 * Created by josue on 21/02/18.
 */
 class CargaImagenes(context:Context): AsyncTask<String, Void, Bitmap>() {
    override fun onPreExecute() {
        var pDialog: ProgressDialog(MainActivity.Th)
    }

    override  fun doInBackground(vararg p0: String?): Bitmap {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
    }

}