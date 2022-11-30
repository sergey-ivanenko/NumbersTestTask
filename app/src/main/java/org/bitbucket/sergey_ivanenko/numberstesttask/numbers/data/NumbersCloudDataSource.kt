package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data

interface NumbersCloudDataSource : FetchNumber {

    suspend fun randomNumber(): NumberData
}