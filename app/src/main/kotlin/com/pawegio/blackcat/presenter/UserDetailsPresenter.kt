package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.kandroid.e
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserDetailsPresenter(private val view: UserDetailsContract.View,
                           private val repository: Repository) : UserDetailsContract.Presenter {

    override fun loadUserDetails(username: String) {
        repository.getUserDetails(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userDetails ->
                            view.updateUserDetails(userDetails)
                        },
                        { error ->
                            e(error.message.toString())
                        }
                )
    }
}