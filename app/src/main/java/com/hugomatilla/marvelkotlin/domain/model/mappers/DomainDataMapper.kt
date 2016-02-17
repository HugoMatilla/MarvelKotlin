package com.hugomatilla.marvelkotlin.domain.model.mappers

import com.hugomatilla.marvelkotlin.data.HeroesListResult
import com.hugomatilla.marvelkotlin.data.Result
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain

/**
 * Created by hugomatilla on 17/02/16.
 */

class DomainDataMapper {

    fun convertFromDataModel(heroes: HeroesListResult): HeroesListDomain {
        return HeroesListDomain(heroes.etag, convertHeroesListToDomain(heroes.data.results))
    }

    private fun convertHeroesListToDomain(list: List<Result>): List<HeroDomain> {
        return list.map { convertHeroItemToDomain(it) }
    }

    private fun convertHeroItemToDomain(hero: Result): HeroDomain {
        return HeroDomain(
                hero.name,
                hero.description,
                hero.thumbnail.path + hero.thumbnail.extension,
                hero.urls[0].url
        )
    }
}