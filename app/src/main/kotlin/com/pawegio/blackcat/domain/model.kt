package com.pawegio.blackcat.domain

import com.google.gson.annotations.SerializedName

/**
 * @author pawegio
 */

interface SearchResult {
    val id: Long
}

data class UserResult(
        override val id: Long,
        val login: String) : SearchResult

data class RepositoryResult(
        override val id: Long,
        val name: String) : SearchResult

data class Users(val items: List<UserResult>)

data class Repositories(val items: List<RepositoryResult>)

data class UserDetails(
        val id: Long,
        val login: String,
        val name: String,
        @SerializedName("avatar_url") val avatarUrl: String?,
        val followers: Int,
        var starredCount: Int?)