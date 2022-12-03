package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface CloudModule {

    fun <T> service(clazz: Class<T>): T

    abstract class Abstract : CloudModule {

        protected abstract val level: HttpLoggingInterceptor.Level
        protected abstract val baseUrl: String

        override fun <T> service(clazz: Class<T>): T {
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(level)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

            return retrofit.create(clazz)
        }
    }

    class Debug : Abstract() {

        override val level = HttpLoggingInterceptor.Level.BODY
        override val baseUrl = BASE_URL_TEST

        companion object {
            private const val BASE_URL_TEST = "http://numbersapi.com/"
        }
    }

    class Release : Abstract() {

        override val level = HttpLoggingInterceptor.Level.NONE
        override val baseUrl = BASE_URL_RELEASE

        companion object {
            private const val BASE_URL_RELEASE = "http://numbersapi.com/"
        }
    }
}