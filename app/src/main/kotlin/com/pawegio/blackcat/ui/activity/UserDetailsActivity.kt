package com.pawegio.blackcat.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pawegio.blackcat.R
import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.RetrofitRepository
import com.pawegio.blackcat.domain.UserDetails
import com.pawegio.blackcat.presenter.UserDetailsPresenter
import kotlinx.android.synthetic.main.activity_user_details.*

/**
 * @author pawegio
 */
class UserDetailsActivity : AppCompatActivity(), UserDetailsContract.View {

    private val presenter by lazy { UserDetailsPresenter(this, RetrofitRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        setSupportActionBar(toolbar)
    }

    override fun updateUserDetails(userDetails: UserDetails) {

    }
}