package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.ui.extensions.DelegatesExt
import kotlinx.android.synthetic.main.settings_activity.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hugomatilla on 25/02/16.
 */
class SettingsActivity : AppCompatActivity() {

    companion object {
        val OFFSET = "offset"
    }

    var offset: Long by DelegatesExt.preference(this, OFFSET, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        offsetView.setText(offset.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        offset = offsetView.text.toString().toLong()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed(); true
        }
        else -> false
    }
}
