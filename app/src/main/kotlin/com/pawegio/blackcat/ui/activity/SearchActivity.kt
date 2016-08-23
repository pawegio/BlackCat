package com.pawegio.blackcat.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.jakewharton.rxbinding.widget.queryTextChanges
import com.pawegio.blackcat.R
import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.RepositoryProvider
import com.pawegio.blackcat.domain.SearchResult
import com.pawegio.blackcat.presenter.SearchPresenter
import com.pawegio.blackcat.ui.adapter.ResultsAdapter
import com.pawegio.blackcat.ui.decoration.DividerItemDecoration
import com.pawegio.kandroid.IntentFor
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * @author pawegio
 */
class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val presenter by lazy { SearchPresenter(this, RepositoryProvider.repository) }
    private val resultsAdapter by lazy { ResultsAdapter { presenter.openUserDetails(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)

        recyclerView.apply {
            adapter = resultsAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity))
            setHasFixedSize(true)
        }

        searchView.queryTextChanges()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    resultsAdapter.clearData()
                }
                .filter { TextUtils.getTrimmedLength(it) >= 3 }
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.searchResults(it)
                }

        tryAgainButton.setOnClickListener {
            val query = searchView.query
            if (TextUtils.getTrimmedLength(query) >= 3) {
                presenter.searchResults(query.toString())
            }
        }
    }

    override fun showProgressBar() {
        hidePlaceholder()
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showResults(results: List<SearchResult>) {
        hidePlaceholder()
        resultsAdapter.updateData(results)
    }

    override fun showResultsPlaceholder(message: String, withButton: Boolean) {
        resultsAdapter.clearData()
        resultsPlaceholder.text = message
        resultsPlaceholder.visibility = View.VISIBLE
        tryAgainButton.visibility = if (withButton) View.VISIBLE else View.GONE
    }

    override fun showUserDetails(username: String) {
        startActivity(IntentFor<UserDetailsActivity>(this).apply {
            putExtra(UserDetailsActivity.KEY_USERNAME, username)
        })
    }

    private fun hidePlaceholder() {
        resultsPlaceholder.visibility = View.GONE
        tryAgainButton.visibility = View.GONE
    }
}
