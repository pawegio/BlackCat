package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.blackcat.domain.UserResult
import com.pawegio.kandroid.e
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchPresenter(private val view: SearchContract.View,
                      private val repository: Repository) : SearchContract.Presenter {

    override fun searchResults(query: String) {
        view.showProgressBar()
        repository.getSearchResults(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { results ->
                            view.hideProgressBar()
                            view.showResults(results)
                        },
                        { error ->
                            e(error.message.toString())
                        }
                )
    }

    override fun openUserDetails(user: UserResult) {
        view.showUserDetails(user.login)
    }
}