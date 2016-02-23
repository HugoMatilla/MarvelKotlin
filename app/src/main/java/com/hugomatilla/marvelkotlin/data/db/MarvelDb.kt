package com.hugomatilla.marvelkotlin.data.db

//import com.hugomatilla.marvelkotlin.domain.extensions.toVarargArray
import com.hugomatilla.marvelkotlin.data.extensions.clear
import com.hugomatilla.marvelkotlin.data.extensions.parseOpt
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
               val dataMapper: DaoDataMapper = DaoDataMapper()) {

    fun requestHeroByName(name: String) = marvelDbHelper.use {

        val query = "${HeroTable.NAME} = ?"
        val hero = select(HeroTable.NAME)
                .whereSimple(query, name)
                .parseOpt { HeroDao(HashMap(it)) }

        if (hero != null) dataMapper.convertHeroDaoToDomain(hero) else null
    }

    fun saveHeroes(heroesList: HeroesListDomain) = marvelDbHelper.use {
        clear(HeroTable.NAME)
        with(dataMapper.convertHeroListFromDomain(heroesList)) {
            forEach { insert(HeroTable.NAME, *it.map.toVarargArray()) }
        }
    }
}
