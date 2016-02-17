package com.hugomatilla.marvelkotlin.data.utils

import com.hugomatilla.marvelkotlin.data.utils.Keys.PK
import com.hugomatilla.marvelkotlin.data.utils.Keys.PRK
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
        val hash = getMd5HashFrom(timeStamp + PrivateKey + PublicKey);
        return String.format(baseUrl, timeStamp, PublicKey, hash);
    }

    fun getMd5HashFrom(s: String): String {
        var messageDigest: MessageDigest? = null
        try {
            messageDigest = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        messageDigest!!.update(s.toByteArray(), 0, s.length)
        val hash = BigInteger(1, messageDigest.digest()).toString(16)
        return hash
    }
}
