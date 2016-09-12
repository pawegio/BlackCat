package com.pawegio.blackcat.contract

import com.pawegio.blackcat.domain.UserDetails
import com.pawegio.blackcat.presenter.BasePresenter

/**
 * @author pawegio
 */
interface UserDetailsContract {

    interface View {

        fun updateUserDetails(userDetails: UserDetails)
    }

    interface Presenter : BasePresenter<View> {

        fun loadUserDetails(username: String)
    }
}