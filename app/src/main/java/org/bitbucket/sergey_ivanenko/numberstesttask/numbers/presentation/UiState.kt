package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation

sealed class UiState {

    // todo views in fragment

    class Success : UiState() {
        override fun equals(other: Any?): Boolean {
            return if (other is Success) {
                true
            } else {
                super.equals(other)
            }
        }
    }

    data class Error(private val message: String) : UiState() {

    }
}