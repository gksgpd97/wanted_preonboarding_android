package com.example.wantedpreonboardingandroid.data

import android.app.Application
import com.example.wantedpreonboardingandroid.BuildConfig
import com.example.wantedpreonboardingandroid.api.NewsService

class NewsRepository(application: Application) {
    suspend fun selectArticles(): DefaultResponse {
        val response = NewsService.selectArticles(BuildConfig.NEWS_API_KEY, "kr")
        return if (response.isSuccessful) response.body() as DefaultResponse else DefaultResponse(
            "fail",
            "",
            "client fail",
            ArrayList()
        )
    }

    suspend fun selectArticlesWithCategory(category: String): DefaultResponse {
        val response =
            NewsService.selectArticlesWithCategory(BuildConfig.NEWS_API_KEY, "kr", category)
        return if (response.isSuccessful) response.body() as DefaultResponse else DefaultResponse(
            "fail",
            "",
            "client fail",
            ArrayList()
        )
    }

    companion object {
        private var instance: NewsRepository? = null
        fun getInstance(application: Application): NewsRepository? {
            if (instance == null) {
                instance = NewsRepository(application)
            }
            return instance
        }
    }
}