package com.hugomatilla.marvelkotlin.domain

import com.hugomatilla.marvelkotlin.domain.model.HeroDomain

/**
 * Created by hugomatilla on 22/02/16.
 */

class RequestHeroUseCase(val name: String,
                         val marvelProvider: MarvelProvider = MarvelProvider()) : IUseCase<HeroDomain> {
    override fun execute(): HeroDomain? {
        return marvelProvider.requestItem(name)
    }
}

