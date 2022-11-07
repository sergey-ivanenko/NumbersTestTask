package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.NumbersResult

interface HandleNumbersRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> NumbersResult
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communications: NumbersCommunications,
        private val numbersResultMapper: NumbersResult.Mapper<Unit>
    ) : HandleNumbersRequest {

        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> NumbersResult) {
            communications.showProgress(true)
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                communications.showProgress(false)
                result.map(numbersResultMapper)
            }
        }
    }
}