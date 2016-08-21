package com.pawegio.blackcat.contract

/**
 * @author pawegio
 */
interface UserDetailsContract {

    interface View {

        fun updateUserDetails(avatarUrl: String?, starredCount: Int, followersCount: Int)
    }

    interface Presenter {

        fun loadUserDetails(username: String)
    }
}