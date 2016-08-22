package com.pawegio.blackcat.domain

import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * @author pawegio
 */
interface GitHubApiService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Observable<Repositories>

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Observable<Users>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<UserDetails>

    @GET("users/{username}/starred?per_page=1")
    fun getStarredRepositoriesByUser(@Path("username") username: String): Observable<Response<JsonArray>>
}