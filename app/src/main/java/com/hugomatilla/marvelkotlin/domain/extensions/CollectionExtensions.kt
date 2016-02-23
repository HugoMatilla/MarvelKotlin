package com.hugomatilla.marvelkotlin.domain.extensions

/**
 * Created by hugomatilla on 23/02/16.
 */

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()
