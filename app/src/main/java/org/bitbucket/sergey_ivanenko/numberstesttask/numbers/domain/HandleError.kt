package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain

import org.bitbucket.sergey_ivanenko.numberstesttask.R
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation.ManageResources

interface HandleError {

    fun handle(e: Exception): String

    class Base(private val manageResources: ManageResources) : HandleError {

        override fun handle(e: Exception): String  = manageResources.string(
            when(e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )
    }
}