package com.hugomatilla.marvelkotlin.domain

import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain

/**
 * Created by hugomatilla on 23/02/16.
 */

interface IMarvelDataSource {
    fun requestHeroesList(): HeroesListDomain?
    fun requestHeroByName(name: String): HeroDomain?
}
