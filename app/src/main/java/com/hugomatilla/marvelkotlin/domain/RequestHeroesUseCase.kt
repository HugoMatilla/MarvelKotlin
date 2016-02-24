package com.hugomatilla.marvelkotlin.domain

import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain

/**
 * Created by hugomatilla on 17/02/16.
 */

class RequestHeroesUseCase(
        val marvelProvider: MarvelProvider = MarvelProvider()) : IUseCase<HeroesListDomain> {
    override fun execute(): HeroesListDomain {
        return marvelProvider.request()
    }
}
