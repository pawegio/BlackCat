package com.pawegio.blackcat.domain

/**
 * @author pawegio
 */

interface SearchResult {
    val id: Long
    val name: String
}

data class User(
        override val id: Long,
        override val name: String) : SearchResult

data class Repository(
        override val id: Long,
        override val name: String) : SearchResult