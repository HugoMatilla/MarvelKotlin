package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.RequestHeroUseCase
import com.hugomatilla.marvelkotlin.domain.RequestHeroesUseCase
import com.hugomatilla.marvelkotlin.ui.adapters.HeroesListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private var syncFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroesListView.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestHeroesUseCase().execute()
            uiThread {
                syncFinished = true //Used for the IdlingResource in Espresso tests
                heroesListView.adapter = HeroesListAdapter(result) {
                    val hero = RequestHeroUseCase(it.name).execute()
                    if (hero != null)
                        startActivity<HeroDetailActivity>(HeroDetailActivity.HERO_NAME to it.name)
                    else
                        toast("Sorry no Hero here :(")
                }
            }
        }
    }

    fun isSyncFinished(): Boolean {
        return syncFinished;
    }
}
