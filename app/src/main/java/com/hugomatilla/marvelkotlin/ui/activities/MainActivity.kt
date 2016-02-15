package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.adapters.MainListAdapter
import com.hugomatilla.marvelkotlin.data.Request
import com.hugomatilla.marvelkotlin.utils.APIUtils
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
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
        listView.adapter = MainListAdapter(items)

        val url = APIUtils.getUrl();
        Log.d(javaClass.simpleName, "URL:" + url)

        async() {
            Request(url).run()
            uiThread {
                Log.d(javaClass.simpleName, "Request performed")
                longToast("Request performed")
            }
        }

    }
}
