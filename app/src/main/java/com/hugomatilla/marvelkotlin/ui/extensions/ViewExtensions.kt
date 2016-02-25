package com.hugomatilla.marvelkotlin.ui.extensions

/**
 * Created by hugomatilla on 17/02/16.
 */

import android.content.Context
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)