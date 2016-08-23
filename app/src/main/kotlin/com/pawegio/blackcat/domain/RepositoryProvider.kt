package com.pawegio.blackcat.domain

/**
 * @author pawegio
 */
object RepositoryProvider {

    private val retrofitRepository: RetrofitRepository by lazy { RetrofitRepository() }

    var testRespository: Repository? = null

    val repository: Repository
        get() = testRespository ?: retrofitRepository
}