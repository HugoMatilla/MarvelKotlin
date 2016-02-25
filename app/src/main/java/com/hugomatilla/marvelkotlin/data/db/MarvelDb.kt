package com.hugomatilla.marvelkotlin.data.db

import com.hugomatilla.marvelkotlin.data.extensions.clear
import com.hugomatilla.marvelkotlin.data.extensions.parseList
import com.hugomatilla.marvelkotlin.data.extensions.parseOpt
import com.hugomatilla.marvelkotlin.domain.IMarvelDataSource
import com.hugomatilla.marvelkotlin.domain.extensions.toVarargArray
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

/**
 * Created by hugomatilla on 22/02/16.
 */


/**
 * Has the functionality to make requests to the database: query and save objects
 */
class MarvelDb(val marvelDbHelper: MarvelDbHelper = MarvelDbHelper.instance,
               val dataMapper: DaoDataMapper = DaoDataMapper()) : IMarvelDataSource {

    override fun requestHeroesList() = marvelDbHelper.use {
        val query = "${HeroTable.IMAGE_URL} not like ?"
        val heroes = select(HeroTable.TABLE_NAME)
                .whereSimple(query, "%image_not_available%")
                .parseList { HeroDao(HashMap(it)) }

        dataMapper.convertHeroListDaoToDomain(heroes)
    }

    override fun requestHeroByName(name: String) = marvelDbHelper.use {
        val query = "${HeroTable.NAME} = ?"
        val hero = select(HeroTable.TABLE_NAME)
                .whereSimple(query, name)
                .parseOpt { HeroDao(HashMap(it)) }

        hero?.let { dataMapper.convertHeroDaoToDomain(hero) }
    }

    fun saveHeroes(heroesList: HeroesListDomain) = marvelDbHelper.use {
        clear(HeroTable.TABLE_NAME)
        with(dataMapper.convertHeroListFromDomain(heroesList)) {
            forEach { insert(HeroTable.TABLE_NAME, *it.map.toVarargArray()) }
        }
    }

}
