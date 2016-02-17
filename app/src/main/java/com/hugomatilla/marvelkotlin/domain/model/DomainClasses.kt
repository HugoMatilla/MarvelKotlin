package com.hugomatilla.marvelkotlin.domain.model

/**
 * Created by hugomatilla on 17/02/16.
 */

data class HeroesListDomain(val etag: String, val heroes: List<HeroDomain>)

data class HeroDomain(val name: String, val description: String, val thumbnail: String, val externalUrl: String)

data class Comic(val name: String, val uri: String)


