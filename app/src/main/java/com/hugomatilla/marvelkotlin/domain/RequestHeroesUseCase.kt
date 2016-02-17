package com.hugomatilla.marvelkotlin.domain

import com.hugomatilla.marvelkotlin.data.Request
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain
import com.hugomatilla.marvelkotlin.domain.model.mappers.DomainDataMapper

/**
 * Created by hugomatilla on 17/02/16.
 */

class RequestHeroesUseCase() : IUseCase<HeroesListDomain> {
    override fun execute(): HeroesListDomain {
        val request = Request()
        return DomainDataMapper().convertFromDataModel(request.execute())
    }
}
