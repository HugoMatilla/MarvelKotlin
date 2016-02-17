package com.hugomatilla.marvelkotlin.data

/**
 * Created by hugomatilla on 15/02/16.
 */

data class HeroesListResult(val code: Int,
                            val status: String,
                            val copyright: String,
                            val attributionText: String,
                            val attributionHTML: String,
                            val etag: String,
                            val data: Data)

data class Data(val offset: Int,
                val limit: Int,
                val total: Int,
                val count: Int,
                val results: List<Result>)

data class Result(val id: Int,
                  val name: String,
                  val description: String,
                  val modified: String,
                  val resourceURI: String,
                  val urls: List<Url>,
                  val thumbnail: Thumbnail,
                  val comics: Comics,
                  val stories: Stories,
                  val events: Events,
                  val series: Series)

data class Url(val type: String,
               val url: String)

data class Thumbnail(
        val path: String,
        val extension: String)

data class Comics(
        val available: Int,
        val returned: Int,
        val collectionURI: String,
        val items: List<Item>)

data class Item(val resourceURI: String,
                val name: String,
                val type: String)

data class Stories(
        val available: Int,
        val returned: Int,
        val collectionURI: String,
        val items: List<Item>)

data class Events(
        val available: Int,
        val returned: Int,
        val collectionURI: String,
        val items: List<Item>)

data class Series(
        val available: Int,
        val returned: Int,
        val collectionURI: String,
        val items: List<Item>)