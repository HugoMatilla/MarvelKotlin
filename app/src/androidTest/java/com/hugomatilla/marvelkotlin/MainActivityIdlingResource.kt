package com.hugomatilla.marvelkotlin

import android.support.test.espresso.IdlingResource

import com.hugomatilla.marvelkotlin.ui.activities.MainActivity

/**
 * Created by hugomatilla on 21/02/16.
 */

class MainActivityIdlingResource(private val activity: MainActivity?) : IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return "MainActivity Idle Name"
    }

    override fun isIdleNow(): Boolean {
        val idle = isIdle
        if (idle) callback!!.onTransitionToIdle()
        return idle
    }

    val isIdle: Boolean
        get() = activity != null && callback != null && activity.isSyncFinished()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }
}