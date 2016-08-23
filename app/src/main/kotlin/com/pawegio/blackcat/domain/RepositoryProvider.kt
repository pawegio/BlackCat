package com.pawegio.blackcat.domain

/**
 * @author pawegio
 */
object RepositoryProvider {

    private val retrofitRepository: RetrofitRepository by lazy { RetrofitRepository() }

    var testRepository: Repository? = null

    val repository: Repository
        get() = testRepository ?: retrofitRepository
}