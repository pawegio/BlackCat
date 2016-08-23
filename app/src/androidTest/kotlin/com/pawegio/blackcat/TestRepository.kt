package com.pawegio.blackcat

import com.pawegio.blackcat.domain.*
import rx.Observable

/**
 * @author pawegio
 */
object TestRepository : Repository {

    override fun getSearchResults(query: String): Observable<List<SearchResult>> {
        return Observable.just(listOf(
                RepositoryResult(1, "$query r1"),
                RepositoryResult(2, "$query r2"),
                UserResult(1, "$query u1"),
                UserResult(3, "$query u3")))
    }

    override fun getUserDetails(username: String): Observable<UserDetails> {
        return Observable.just(UserDetails(1, username, "John Doe", "http://avatar.ar", 5, 0))
    }
}