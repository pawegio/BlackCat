package com.pawegio.blackcat.presenter

import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.Repository
import com.pawegio.blackcat.domain.UserDetails
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.*
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import rx.Observable
import kotlin.properties.Delegates

/**
 * @author pawegio
 */
class UserDetailsPresenterTest {

    private var repository: Repository by Delegates.notNull()
    private var view: UserDetailsContract.View by Delegates.notNull()
    private var presenter: UserDetailsContract.Presenter by Delegates.notNull()

    @Before
    fun setUp() {
        repository = mock()
        view = mock()
        presenter = UserDetailsPresenter(view, repository)
    }

    @Test
    fun testLoadUserDetails() {
        val username = "username1"
        val userDetails = UserDetails(1, username, "John Nowak", "http://avatar.ar", 3, 7)
        `when`(repository.getUserDetails(anyString()))
                .thenReturn(Observable.just(userDetails))
        presenter.loadUserDetails(username)

        verify(view).updateUserDetails(userDetails)
    }

    inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
}