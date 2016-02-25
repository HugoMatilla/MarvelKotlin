package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.RequestHeroUseCase
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_detail_activity.*
import org.jetbrains.anko.async
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 25/02/16.
 */

class HeroDetailActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        val ID = "HeroActivity:id"
        val HERO_NAME = "HeroActivity:heroName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_detail_activity)
        initToolbar()
        toolbarTitle = intent.getStringExtra(HERO_NAME)
        enableHomeAsUp { onBackPressed() }

        async() {
            val result = RequestHeroUseCase(intent.getStringExtra(HERO_NAME)).execute()
            uiThread { bindHero(result!!) }
        }
    }

    private fun bindHero(hero: HeroDomain) = with(hero) {
        Picasso.with(ctx).load(imageUrl).into(heroDetailImageView)
        toolbar.subtitle = id.toString()
        heroDetailNameView.text = name
        heroDetailDescriptionView.text = description
    }
}
