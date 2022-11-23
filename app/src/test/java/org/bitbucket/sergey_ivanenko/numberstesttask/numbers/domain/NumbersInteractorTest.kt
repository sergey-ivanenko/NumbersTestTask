package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain

import kotlinx.coroutines.runBlocking
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation.ManageResources
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NumbersInteractorTest {

    private lateinit var interactor: NumbersInteractor
    private lateinit var repository: TestNumbersRepository
    private lateinit var manageResources: TestManageResources

    @Before
    fun setUp() {
        manageResources = TestManageResources()
        repository = TestNumbersRepository()
        interactor = NumbersInteractor.Base(
            repository,
            HandleRequest.Base(HandleError.Base(manageResources), repository)
        )
    }

    @Test
    fun test_init_success() = runBlocking {
        repository.changeExpectedList(listOf(NumberFact("6", "fact about 6")))
        val actual = interactor.init()
        val expected = NumbersResult.Success(listOf(NumberFact("6", "fact about 6")))
        assertEquals(expected, actual)
        assertEquals(1, repository.allNumbersCalledCount)
    }

    @Test
    fun test_fact_about_number_success(): Unit = runBlocking {
        repository.changeExpectedFactAboutOfNumber(NumberFact("7", "fact about 7"))

        val actual = interactor.factAboutNumber("7")
        val expected = NumbersResult.Success(listOf(NumberFact("7", "fact about 7")))

        assertEquals(expected, actual)
        assertEquals("7", repository.numberFactCalledCountList[0])
        assertEquals(1, repository.numberFactCalledCountList.size)
    }

    @Test
    fun test_fact_about_number_error(): Unit = runBlocking {
        repository.expectingErrorGetFact(true)
        manageResources.changeExpected("No internet connection")

        val actual = interactor.factAboutNumber("7")
        val expected = NumbersResult.Failure("No internet connection")

        assertEquals(expected, actual)
        assertEquals("7", repository.numberFactCalledCountList[0])
        assertEquals(1, repository.numberFactCalledCountList.size)
    }

    @Test
    fun test_fact_about_random_number_success(): Unit = runBlocking {
        repository.changeExpectedFactAboutOfRandomNumber(NumberFact("7", "fact about 7"))

        val actual = interactor.factAboutRandomNumber()
        val expected = NumbersResult.Success(listOf(NumberFact("7", "fact about 7")))

        assertEquals(expected, actual)
        assertEquals(1, repository.randomNumberFactCalledCountList.size)
    }

    @Test
    fun test_fact_about_random_number_error(): Unit = runBlocking {
        repository.expectingErrorGetRandomFact(true)
        manageResources.changeExpected("No internet connection")

        val actual = interactor.factAboutRandomNumber()
        val expected = NumbersResult.Failure("No internet connection")

        assertEquals(expected, actual)
        assertEquals(1, repository.randomNumberFactCalledCountList.size)
    }

    private class TestNumbersRepository : NumbersRepository {

        private val allNumbers = mutableListOf<NumberFact>()
        private var numberFact = NumberFact("", "")
        private var errorWhileNumberFact = false

        var allNumbersCalledCount = 0
        val numberFactCalledCountList = mutableListOf<String>()
        val randomNumberFactCalledCountList = mutableListOf<String>()

        fun changeExpectedList(list: List<NumberFact>) {
            allNumbers.clear()
            allNumbers.addAll(list)
        }

        fun changeExpectedFactAboutOfNumber(numberFact: NumberFact) {
            this.numberFact = numberFact
        }

        fun changeExpectedFactAboutOfRandomNumber(numberFact: NumberFact) {
            this.numberFact = numberFact
        }

        fun expectingErrorGetFact(error: Boolean) {
            errorWhileNumberFact = error
        }

        fun expectingErrorGetRandomFact(error: Boolean) {
            errorWhileNumberFact = error
        }

        override suspend fun allNumbers(): List<NumberFact> {
            allNumbersCalledCount++
            return allNumbers
        }

        override suspend fun numberFact(number: String): NumberFact {
            numberFactCalledCountList.add(number)
            if (errorWhileNumberFact) throw NoInternetConnectionException()
            allNumbers.add(numberFact)

            return numberFact
        }

        override suspend fun randomNumberFact(): NumberFact {
            randomNumberFactCalledCountList.add("")
            if (errorWhileNumberFact) throw NoInternetConnectionException()
            allNumbers.add(numberFact)

            return numberFact
        }
    }

    private class TestManageResources : ManageResources {

        private var value = ""

        fun changeExpected(string: String) {
            value = string
        }

        override fun string(id: Int): String = value
    }
}