package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.RequestHeroesUseCase
import com.hugomatilla.marvelkotlin.ui.adapters.HeroesListAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private var syncFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: RecyclerView = find(R.id.main_list)
        listView.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestHeroesUseCase().execute()
            uiThread {
                syncFinished = true
                listView.adapter = HeroesListAdapter(result) { toast(it.name) }
            }
        }
    }

    fun isSyncFinished(): Boolean {
        return syncFinished;
    }
}
