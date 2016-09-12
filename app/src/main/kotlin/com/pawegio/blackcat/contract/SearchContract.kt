package com.pawegio.blackcat.contract

import com.pawegio.blackcat.domain.SearchResult
import com.pawegio.blackcat.domain.UserResult
import com.pawegio.blackcat.presenter.BasePresenter

/**
 * @author pawegio
 */
interface SearchContract {

    interface View {

        fun showProgressBar()

        fun hideProgressBar()

        fun showResults(results: List<SearchResult>)

        fun showResultsPlaceholder(message: String, withButton: Boolean = false)

        fun showUserDetails(username: String)
    }

    interface Presenter : BasePresenter<View> {

        fun searchResults(query: String)

        fun openUserDetails(user: UserResult)
    }
}