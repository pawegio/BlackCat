package com.pawegio.blackcat.domain

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

data class UserDetails(
        val id: Long,
        val login: String,
        val name: String,
        val avatarUrl: String?,
        val followersCount: Int,
        val starredCount: Int)