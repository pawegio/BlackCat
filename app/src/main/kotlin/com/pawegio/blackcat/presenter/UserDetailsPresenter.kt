package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.UserDetailsContract

class UserDetailsPresenter(private val view: UserDetailsContract.View) : UserDetailsContract.Presenter {

    override fun loadUserDetails(username: String) {

    }
}