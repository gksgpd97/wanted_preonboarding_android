package com.example.wantedpreonboardingandroid.data

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wantedpreonboardingandroid.BuildConfig
import com.example.wantedpreonboardingandroid.api.NewsService
import com.example.wantedpreonboardingandroid.utilities.PagingSource
import javax.inject.Inject

class NewsRepository @Inject constructor(application: Application) {
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