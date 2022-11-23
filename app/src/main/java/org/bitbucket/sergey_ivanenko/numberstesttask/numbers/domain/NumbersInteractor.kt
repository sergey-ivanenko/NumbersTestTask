package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain

interface NumbersInteractor {

    suspend fun init(): NumbersResult

    suspend fun factAboutNumber(number: String): NumbersResult

    suspend fun factAboutRandomNumber(): NumbersResult

    class Base(
        private val repository: NumbersRepository,
        private val handleRequest: HandleRequest
    ) : NumbersInteractor {

        override suspend fun init(): NumbersResult = NumbersResult.Success(repository.allNumbers())

        override suspend fun factAboutNumber(number: String): NumbersResult = handleRequest.handle {
            repository.numberFact(number)
        }

        override suspend fun factAboutRandomNumber(): NumbersResult  = handleRequest.handle {
            repository.randomNumberFact()
        }
    }
}

interface HandleRequest {

    suspend fun handle(block: suspend () -> Unit): NumbersResult

    class Base(
        private val handleError: HandleError,
        private val repository: NumbersRepository
    ) : HandleRequest {

        override suspend fun handle(block: suspend () -> Unit): NumbersResult = try {
            block.invoke()
            NumbersResult.Success(repository.allNumbers())
        } catch (e: Exception) {
            NumbersResult.Failure(handleError.handle(e))
        }
    }
}