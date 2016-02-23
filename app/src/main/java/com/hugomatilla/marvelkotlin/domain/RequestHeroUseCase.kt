package com.hugomatilla.marvelkotlin.domain

import com.hugomatilla.marvelkotlin.data.cloud.Request
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain
import com.hugomatilla.marvelkotlin.domain.model.mappers.DomainDataMapper

/**
 * Created by hugomatilla on 22/02/16.
 */

class RequestHeroUseCase(val name: String) : IUseCase<HeroesListDomain> {
    //Todo finish
    override fun execute(): HeroesListDomain {
        val request = Request()
        return DomainDataMapper().convertFromDataModel(request.execute())
    }
}
