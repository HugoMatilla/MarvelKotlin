package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.adapters.HeroesListAdapter
import com.hugomatilla.marvelkotlin.domain.RequestHeroesUseCase
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {


    private val items = listOf(
            "01. Wolverine", "02. Thing", "03. Kitty Pryde", "04. Magneto", "05. Nightcrawler",
            "06. Juggernaut", "07. Emma Frost", "08. Beast", "09. Captain America", "10. Spider-Man",
            "11. Puck", "12. Alex Wilder", "13. Doctor Strange", "14. Cyclops", "15. Colossus",
            "16. Jubilee", "17. Lockheed", "18. Nick Fury", "19. Nico Minoru", "20. Shaman"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: RecyclerView = find(R.id.main_list)
        listView.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestHeroesUseCase().execute()
            uiThread {
                listView.adapter = HeroesListAdapter(result)
            }

        }

    }
}
