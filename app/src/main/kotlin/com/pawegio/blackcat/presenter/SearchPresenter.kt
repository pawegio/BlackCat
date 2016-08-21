package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.User

class SearchPresenter(private val view: SearchContract.View) : SearchContract.Presenter {

    override fun searchResults(query: String) {

    }

    override fun openUserDetails(user: User) {

    }
}