package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.blackcat.domain.UserResult

class SearchPresenter(private val view: SearchContract.View,
                      private val repository: Repository) : SearchContract.Presenter {

    override fun searchResults(query: String) {

    }

    override fun openUserDetails(user: UserResult) {

    }
}