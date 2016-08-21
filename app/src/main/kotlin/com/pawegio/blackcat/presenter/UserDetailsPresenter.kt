package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.Repository

class UserDetailsPresenter(private val view: UserDetailsContract.View,
                           private val repository: Repository) : UserDetailsContract.Presenter {

    override fun loadUserDetails(username: String) {

    }
}