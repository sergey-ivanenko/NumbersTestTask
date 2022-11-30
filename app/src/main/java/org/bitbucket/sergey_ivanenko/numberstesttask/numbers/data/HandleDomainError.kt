package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data

import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.HandleError
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.NoInternetConnectionException
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDomainError : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}