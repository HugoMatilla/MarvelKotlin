package com.hugomatilla.marvelkotlin.data.db

import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain

/**
 * Created by hugomatilla on 22/02/16.
 */

/**
 * Converts data from Domain Layer to Dao. Database access objects.
 */
class DaoDataMapper {
    //The DB will no have any heroList element, the only parameter every list has is the etag. It would be save somewhere else.
    fun convertHeroListFromDomain(heroesList: HeroesListDomain) =
            with(heroesList) {
                val heroes = heroesList.heroes.map { convertHeroFromDomain(it) }
                heroes
            }

    private fun convertHeroFromDomain(hero: HeroDomain) =
            with(hero) { HeroDao(name, description, imageUrl, externalUrl) }

    fun convertHeroListDaoToDomain(heroesList: List<HeroDao>) = with(heroesList) {
        val heroes = heroesList.map { convertHeroDaoToDomain(it) }
        HeroesListDomain(getEtag(), heroes)
    }

    fun convertHeroDaoToDomain(hero: HeroDao) = with(hero) {
        HeroDomain(name, description, imageUrl, externalUrl)
    }

    private fun getEtag() = ""
}
