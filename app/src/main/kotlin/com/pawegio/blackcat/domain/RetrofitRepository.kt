package com.pawegio.blackcat.domain

import com.google.gson.JsonArray
import retrofit2.Response
import rx.Observable

class RetrofitRepository : Repository {

    override fun getSearchResults(query: String): Observable<List<SearchResult>> {
        return Observable.zip(
                GitHubRetrofit.service.searchRepositories(query),
                GitHubRetrofit.service.searchUsers(query)) { repositories, users ->
            (repositories.items + users.items).sortedBy { it.id }
        }
    }

    override fun getUserDetails(username: String): Observable<UserDetails> {
        return GitHubRetrofit.service.getUser(username).zipWith(
                GitHubRetrofit.service.getStarredRepositoriesByUser(username)) { user, starredResponse ->
            user.apply { starredCount = parseStarredCount(starredResponse) }
        }
    }

    private fun parseStarredCount(response: Response<JsonArray>): Int {
        val link = response.headers().get("Link")
        if (link != null) {
            val begin = link.lastIndexOf("page") + 5
            val end = link.lastIndexOf(">")
            return link.substring(begin, end).toInt()
        } else {
            return 0
        }
    }
}