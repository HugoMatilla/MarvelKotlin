package com.hugomatilla.marvelkotlin.utils

import com.hugomatilla.marvelkotlin.utils.Keys.PK
import com.hugomatilla.marvelkotlin.utils.Keys.PRK
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * Created by hugomatilla on 08/02/16.
 */
object APIUtils {

    private val baseUrl = "https://gateway.marvel.com:443/v1/public/characters?ts=%s&limit=100&apikey=%s&hash=%s"
    private val PrivateKey = PRK;
    private val PublicKey = PK;


    fun getUrl(): String {
        val timeStamp = Date().time.toLong().toString()
        val hash = APIUtils.getMd5HashFrom(timeStamp + PrivateKey + PublicKey);
        return String.format(baseUrl, timeStamp, PublicKey, hash);
    }

    fun getMd5HashFrom(s: String): String {
        var m: MessageDigest? = null
        try {
            m = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        m!!.update(s.toByteArray(), 0, s.length)
        val hash = BigInteger(1, m.digest()).toString(16)
        return hash
    }
}
