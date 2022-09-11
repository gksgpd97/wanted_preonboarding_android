package com.example.wantedpreonboardingandroid.api

import com.example.wantedpreonboardingandroid.data.DefaultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/top-headlines")
    suspend fun selectArticles(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Response<DefaultResponse>

    @GET("/v2/top-headlines")
    suspend fun selectArticlesTmp(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<DefaultResponse>


    @GET("/v2/top-headlines")
    suspend fun selectArticlesWithCategory(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String
    ): Response<DefaultResponse>

    companion object {
        suspend fun selectArticles(apiKey: String, country: String): Response<DefaultResponse> {
            return ApiClient.create(NewsService::class.java).selectArticles(apiKey, country)
        }

//        suspend fun selectArticlesTmp(apiKey: String, country: String, page: Int, pageSize: Int): Response<DefaultResponse> {
//            return ApiClient.create(NewsService::class.java).selectArticlesTmp(apiKey, country, page, pageSize)
//        }

        suspend fun selectArticlesWithCategory(
            apiKey: String,
            country: String,
            category: String
        ): Response<DefaultResponse> {
            return ApiClient.create(NewsService::class.java)
                .selectArticlesWithCategory(apiKey, country, category)
        }
    }
}