package com.hugomatilla.marvelkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.RequestHeroUseCase
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_detail_activity.*
import org.jetbrains.anko.async
import org.jetbrains.anko.ctx
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 25/02/16.
 */

class HeroDetailActivity : AppCompatActivity() {

    companion object {
        val ID = "HeroActivity:id"
        val HERO_NAME = "HeroActivity:heroName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_detail_activity)

        title = intent.getStringExtra(HERO_NAME)

        async() {
            val result = RequestHeroUseCase(intent.getStringExtra(HERO_NAME)).execute()
            uiThread { bindHero(result!!) }
        }
    }

    private fun bindHero(hero: HeroDomain) = with(hero) {
        Picasso.with(ctx).load(imageUrl).into(heroDetailImageView)
        supportActionBar?.subtitle = id.toString()
        heroDetailNameView.text = name
        heroDetailDescriptionView.text = description
    }
}
