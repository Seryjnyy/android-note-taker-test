package com.seryjnyy.notetaker.domain.utils

sealed class RepoResult<out T> {
    /** Represents a success with payload [T]. */
    data class Success<out T>(
        val data: T,
    ) : RepoResult<T>()


    /** Represents a failure. */
    data class Failure(
        val error: Throwable,
    ) : RepoResult<Nothing>()
}