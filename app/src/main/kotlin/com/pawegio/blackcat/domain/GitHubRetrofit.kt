package com.pawegio.blackcat.domain

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author pawegio
 */
object GitHubRetrofit {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    val retrofitService: GitHubApiService by lazy { retrofit.create(GitHubApiService::class.java) }

    var testService: GitHubApiService? = null

    val service: GitHubApiService
        get() = testService ?: retrofitService
}