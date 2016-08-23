package com.pawegio.blackcat

import com.google.gson.JsonArray
import com.pawegio.blackcat.domain.*
import retrofit2.Response
import rx.Observable

/**
 * @author pawegio
 */
object TestGitHubApiService : GitHubApiService {

    override fun searchRepositories(query: String): Observable<Repositories> {
        return Observable.just(Repositories(listOf(
                RepositoryResult(1, "$query r1"),
                RepositoryResult(2, "$query r2"))))
    }

    override fun searchUsers(query: String): Observable<Users> {
        return Observable.just(Users(listOf(
                UserResult(1, "$query u1"),
                UserResult(3, "$query u3"))))
    }

    override fun getUser(username: String): Observable<UserDetails> {
        return Observable.just(UserDetails(1, username, "John Doe", "http://avatar.ar", 5, null))
    }

    override fun getStarredRepositoriesByUser(username: String): Observable<Response<JsonArray>> {
        return Observable.just(Response.success(JsonArray()))
    }
}