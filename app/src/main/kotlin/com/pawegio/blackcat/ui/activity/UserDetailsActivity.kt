package com.pawegio.blackcat.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.pawegio.blackcat.R
import com.pawegio.blackcat.contract.UserDetailsContract
import com.pawegio.blackcat.domain.RepositoryProvider
import com.pawegio.blackcat.domain.UserDetails
import com.pawegio.blackcat.presenter.UserDetailsPresenter
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.content_user_details.*

/**
 * @author pawegio
 */
class UserDetailsActivity : AppCompatActivity(), UserDetailsContract.View {

    companion object {
        const val KEY_USERNAME = "username"
    }

    private val presenter by lazy { UserDetailsPresenter(RepositoryProvider.repository) }
    private val username by lazy { intent.extras.getString(KEY_USERNAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        presenter.takeView(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        presenter.loadUserDetails(username)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun updateUserDetails(userDetails: UserDetails) {
        with(userDetails) {
            if (name.isNullOrBlank()) {
                nameView.visibility = View.GONE
                usernameView.text = username
            } else {
                nameView.text = name
                usernameView.text = "($username)"
            }
            followersView.text = "Followers: $followers"
            starredView.text = "Starred: $starredCount"
            if (avatarUrl != null) {
                Glide.with(this@UserDetailsActivity).load(avatarUrl)
                        .centerCrop().into(avatarView)
            }
        }
    }
}