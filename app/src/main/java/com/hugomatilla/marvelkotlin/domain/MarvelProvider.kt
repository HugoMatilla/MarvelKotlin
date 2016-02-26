package com.hugomatilla.marvelkotlin.domain

import com.hugomatilla.marvelkotlin.data.cloud.MarvelCloud
import com.hugomatilla.marvelkotlin.data.db.MarvelDb
import com.hugomatilla.marvelkotlin.domain.extensions.firstResult
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain

/**
 * Created by hugomatilla on 23/02/16.
 */


class MarvelProvider(val sources: List<IMarvelDataSource> = MarvelProvider.SOURCES) {

    companion object {
        val SOURCES = listOf(MarvelDb(), MarvelCloud())
    }

    fun request(): HeroesListDomain = requestToSources { it.requestHeroesList() }
    fun requestWithOffset(offset: Long): HeroesListDomain = MarvelCloud().requestHeroesList(offset)!!

    fun requestItem(name: String): HeroDomain? = requestToSources { it.requestHeroByName(name) }


    private fun <T : Any> requestToSources(f: (IMarvelDataSource) -> T?): T = sources.firstResult { f(it) }
}
