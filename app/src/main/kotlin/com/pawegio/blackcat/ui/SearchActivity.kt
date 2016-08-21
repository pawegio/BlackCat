package com.pawegio.blackcat.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.pawegio.blackcat.R
import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.SearchResult
import com.pawegio.blackcat.presenter.SearchPresenter
import kotlinx.android.synthetic.main.activity_search.*

/**
 * @author pawegio
 */
class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val presenter by lazy { SearchPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {

    }

    override fun showResults(results: List<SearchResult>) {

    }

    override fun showUserDetails(username: String) {

    }
}
