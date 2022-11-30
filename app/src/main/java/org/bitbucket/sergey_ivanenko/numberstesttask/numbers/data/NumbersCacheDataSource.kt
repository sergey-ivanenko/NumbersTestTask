package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data

interface NumbersCacheDataSource : FetchNumber {

    suspend fun allNumbers(): List<NumberData>

    suspend fun contains(number: String): Boolean

    suspend fun saveNumber(numberData: NumberData)
}

interface FetchNumber {
    suspend fun number(number: String): NumberData
}