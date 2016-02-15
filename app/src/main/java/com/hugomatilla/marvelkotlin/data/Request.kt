package com.hugomatilla.marvelkotlin.data

/**
 * Created by hugomatilla on 08/02/16.
 */

import android.util.Log
import java.net.URL

class Request(val url: String) {

    fun run() {
        val jsonString = URL(url).readText()
        Log.d(javaClass.simpleName, jsonString)
    }

}
