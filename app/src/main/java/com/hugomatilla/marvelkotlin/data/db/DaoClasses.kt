package com.hugomatilla.marvelkotlin.data.db

/**
 * Created by hugomatilla on 22/02/16.
 */

import java.util.*

// We wont need the HeroesListDao The Database will only have Hero elements. The etag will be safe not in the DB
class HeroesListDao(val map: MutableMap<String, Any?>, val heroes: List<HeroDao>) {
    var _id: Long by map
    var etag: String by map

    constructor(id: Long, etag: String, heroes: List<HeroDao>)
    : this(HashMap(), heroes) {
        this._id = id
        this.etag = etag
    }
}

class HeroDao(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var name: String by map
    var description: String by map
    var imageUrl: String by map
    var externalUrl: String by map

    constructor(name: String, description: String, imageUrl: String, externalUrl: String)
    : this(HashMap()) {
        this.name = name
        this.description = description
        this.imageUrl = imageUrl
        this.externalUrl = externalUrl
    }
}
