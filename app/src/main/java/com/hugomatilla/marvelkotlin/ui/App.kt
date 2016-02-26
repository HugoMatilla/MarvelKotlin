package com.hugomatilla.marvelkotlin.ui

/**
 * Created by hugomatilla on 22/02/16.
 */

import android.app.Application
import com.facebook.stetho.Stetho
import com.hugomatilla.marvelkotlin.ui.extensions.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(instance);
    }
}
