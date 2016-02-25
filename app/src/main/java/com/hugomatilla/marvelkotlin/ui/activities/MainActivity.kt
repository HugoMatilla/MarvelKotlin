package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.RequestHeroUseCase
import com.hugomatilla.marvelkotlin.domain.RequestHeroesUseCase
import com.hugomatilla.marvelkotlin.ui.adapters.HeroesListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private var syncFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()


        heroesListView.layoutManager = LinearLayoutManager(this)
        attachToScroll(heroesListView)
        toolbarTitle = "Marvel"

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
