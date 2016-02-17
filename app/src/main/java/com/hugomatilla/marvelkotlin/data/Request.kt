package com.hugomatilla.marvelkotlin.data

/**
 * Created by hugomatilla on 08/02/16.
 */

import com.google.gson.Gson
import com.hugomatilla.marvelkotlin.data.utils.APIUtils
import java.net.URL

class Request() {

    fun execute(): HeroesListResult {
        val jsonStr = URL(APIUtils.getUrl()).readText()
        return Gson().fromJson(jsonStr, HeroesListResult::class.java)
    }
}
