package com.pawegio.blackcat.contract

import com.pawegio.blackcat.domain.UserDetails

/**
 * @author pawegio
 */
interface UserDetailsContract {

    interface View {

        fun updateUserDetails(userDetails: UserDetails)
    }

    interface Presenter {

        fun loadUserDetails(username: String)
    }
}