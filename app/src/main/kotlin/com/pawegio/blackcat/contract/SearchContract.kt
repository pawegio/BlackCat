package com.pawegio.blackcat.contract

import com.pawegio.blackcat.domain.SearchResult
import com.pawegio.blackcat.domain.UserResult

/**
 * @author pawegio
 */
interface SearchContract {

    interface View {

        fun showProgressBar()

        fun hideProgressBar()

        fun showResults(results: List<SearchResult>)

        fun showResultsPlaceholder(message: String)

        fun showUserDetails(username: String)
    }

    interface Presenter {

        fun searchResults(query: String)

        fun openUserDetails(user: UserResult)
    }
}