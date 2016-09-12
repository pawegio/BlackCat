package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.SearchContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.blackcat.domain.RepositoryResult
import com.pawegio.blackcat.domain.SearchResult
import com.pawegio.blackcat.domain.UserResult
import com.pawegio.blackcat.mock
import com.pawegio.blackcat.mockRxSchedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import rx.Observable
import kotlin.properties.Delegates

/**
 * @author pawegio
 */
class SearchPresenterTest {

    private var repository: Repository by Delegates.notNull()
    private var view: SearchContract.View by Delegates.notNull()
    private var presenter: SearchContract.Presenter by Delegates.notNull()

    @Before
    fun setUp() {
        repository = mock()
        view = mock()
        presenter = SearchPresenter(repository)
        presenter.takeView(view)
        mockRxSchedulers()
    }

    @Test
    fun testNoResults() {
        val results = emptyList<SearchResult>()
        `when`(repository.getSearchResults(anyString()))
                .thenReturn(Observable.just(results))
        val query = "query with no results"
        presenter.searchResults(query)

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showResultsPlaceholder("No results")
    }

    @Test
    fun testSingleUserResult() {
        val results = listOf(UserResult(1, "user1"))
        `when`(repository.getSearchResults(anyString()))
                .thenReturn(Observable.just(results))
        val query = "user1"
        presenter.searchResults(query)

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showResults(results)
    }

    @Test
    fun testSingleRepositoryResult() {
        val results = listOf(RepositoryResult(1, "repo1"))
        `when`(repository.getSearchResults(anyString()))
                .thenReturn(Observable.just(results))
        val query = "repo1"
        presenter.searchResults(query)

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showResults(results)
    }

    @Test
    fun testMultipleResults() {
        val results = listOf(
                RepositoryResult(1, "kotlin"),
                UserResult(1, "kotlin enthusiast"),
                RepositoryResult(2, "kotlin-koans"))
        `when`(repository.getSearchResults(anyString()))
                .thenReturn(Observable.just(results))
        val query = "kotlin"
        presenter.searchResults(query)

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showResults(results)
    }

    @Test
    fun testOpenUserDetails() {
        val user = UserResult(1, "user1")
        presenter.openUserDetails(user)

        verify(view).showUserDetails(user.login)
    }
}