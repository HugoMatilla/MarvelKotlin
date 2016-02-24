package com.hugomatilla.marvelkotlin.data.cloud

import com.hugomatilla.marvelkotlin.data.db.MarvelDb
import com.hugomatilla.marvelkotlin.domain.IMarvelDataSource
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain
import com.hugomatilla.marvelkotlin.domain.model.mappers.DomainDataMapper

/**
 * Created by hugomatilla on 23/02/16.
 */
class MarvelCloud(val dataMapper: DomainDataMapper = DomainDataMapper(),
                  val db: MarvelDb = MarvelDb()) : IMarvelDataSource {

    override fun requestHeroesList(): HeroesListDomain? {
        val result = Request().execute()
        val converted = dataMapper.convertFromDataModel(result)
        db.saveHeroes(converted)
        return db.requestHeroesList()
    }

    override fun requestHeroByName(name: String): HeroDomain? {
        return null
    }
}