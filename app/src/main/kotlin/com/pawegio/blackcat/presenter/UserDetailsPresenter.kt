package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.kandroid.e
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class UserDetailsPresenter(private val repository: Repository) : UserDetailsContract.Presenter {

    override var view: UserDetailsContract.View? = null

    private val compositeSubscription = CompositeSubscription()

    override fun dropView() {
        super.dropView()
        compositeSubscription.unsubscribe()
    }

    override fun loadUserDetails(username: String) {
        val subscription = repository.getUserDetails(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userDetails ->
                            view?.updateUserDetails(userDetails)
                        },
                        { error ->
                            e(error.message.toString())
                        }
                )
        compositeSubscription.add(subscription)
    }
}