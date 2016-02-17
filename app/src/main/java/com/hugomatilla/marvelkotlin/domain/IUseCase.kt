package com.hugomatilla.marvelkotlin.domain

/**
 * Created by hugomatilla on 17/02/16.
 */

interface IUseCase<T> {
    fun execute(): T
}
