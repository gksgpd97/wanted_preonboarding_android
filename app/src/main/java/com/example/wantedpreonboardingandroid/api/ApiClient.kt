package com.example.wantedpreonboardingandroid.api

import com.example.wantedpreonboardingandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL =
        "https://newsapi.org"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
                .setLevel(HttpLoggingInterceptor.Level.BODY)
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        )
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return getRetrofit().create(service)
    }
}