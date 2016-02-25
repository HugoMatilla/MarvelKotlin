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

    //    fun request(): HeroesListDomain = MarvelCloud().requestHeroesList()!!
    fun request(): HeroesListDomain = sources.firstResult { requestSource(it) }

    fun requestItem(name: String): HeroDomain? = MarvelDb().requestHeroByName(name)

    private fun requestSource(source: IMarvelDataSource): HeroesListDomain? {
        val res = source.requestHeroesList()
        return if (res != null) res else null
    }
}
