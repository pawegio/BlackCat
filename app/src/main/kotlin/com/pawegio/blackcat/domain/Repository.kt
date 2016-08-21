package com.pawegio.blackcat.domain

import rx.Observable

/**
 * @author pawegio
 */
interface Repository {

    fun getSearchResults(query: String): Observable<List<SearchResult>>

    fun getUserDetails(username: String): Observable<UserDetails>
}