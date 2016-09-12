package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.blackcat.domain.UserResult
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class SearchPresenter(private val repository: Repository) : SearchContract.Presenter {

    override var view: SearchContract.View? = null

    private val compositeSubscription = CompositeSubscription()

    override fun dropView() {
        super.dropView()
        compositeSubscription.unsubscribe()
    }

    override fun searchResults(query: String) {
        view?.showProgressBar()
        val subscription = repository.getSearchResults(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { results ->
                            view?.hideProgressBar()
                            if (results.isNotEmpty()) {
                                view?.showResults(results)
                            } else {
                                view?.showResultsPlaceholder("No results")
                            }
                        },
                        { error ->
                            view?.hideProgressBar()
                            view?.showResultsPlaceholder(parseErrorMessage(error.message), withButton = true)
                        }
                )
        compositeSubscription.add(subscription)
    }

    override fun openUserDetails(user: UserResult) {
        view?.showUserDetails(user.login)
    }

    private fun parseErrorMessage(errorMessage: String?): String {
        return if (errorMessage?.contains("403") ?: false) {
            "GitHub API rate limit exceeded. Hold on :-)"
        } else {
            "Unknown error occurred :-("
        }
    }
}