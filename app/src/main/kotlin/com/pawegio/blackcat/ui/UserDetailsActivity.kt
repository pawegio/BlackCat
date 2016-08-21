package com.pawegio.blackcat.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.UserDetails
import com.pawegio.blackcat.presenter.UserDetailsPresenter

/**
 * @author pawegio
 */
class UserDetailsActivity : AppCompatActivity(), UserDetailsContract.View {

//    private val presenter by lazy { UserDetailsPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun updateUserDetails(userDetails: UserDetails) {

    }
}