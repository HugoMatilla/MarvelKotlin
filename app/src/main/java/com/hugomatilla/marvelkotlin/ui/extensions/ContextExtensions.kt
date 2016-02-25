package com.hugomatilla.marvelkotlin.ui.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by hugomatilla on 25/02/16.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)
