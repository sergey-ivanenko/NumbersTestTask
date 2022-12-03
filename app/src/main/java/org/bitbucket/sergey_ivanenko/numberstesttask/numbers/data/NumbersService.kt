package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersService {

    @GET("{id}")
    suspend fun fact(@Path("id") id: String): String

    @GET("random/math")
    suspend fun random(): Response<String>
}