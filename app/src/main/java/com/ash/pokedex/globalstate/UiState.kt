package com.ash.pokedex.globalstate

sealed class UiState<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val isDisconnected: Boolean = false,
) {
    class Loading<T> : UiState<T>()

    class Success<T>(data: T?) : UiState<T>(data)

    class Error<T>(
        message: String = "",
    ) : UiState<T>(
            errorMessage = message,
        )
}
