package com.example.wantedpreonboardingandroid.data

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wantedpreonboardingandroid.api.NewsService
import com.example.wantedpreonboardingandroid.utilities.PagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImplTmp constructor(private val application: Application, private val service: NewsService) {


    //    suspend fun selectArticles(): DefaultResponse {
//        val response = NewsService.selectArticles(BuildConfig.NEWS_API_KEY, "kr")
//        return if (response.isSuccessful) response.body() as DefaultResponse else DefaultResponse(
//            "fail",
//            "",
//            "client fail",
//            ArrayList()
//        )
//    }
//
//    suspend fun selectArticlesTmp(): LiveData<PagingData<Article>> {
//        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
//            pagingSourceFactory = { PagingSource(service) }).liveData
//    }
//
//
//    fun selectArticlesTmp2(): Flow<PagingData<Article>> {
//        Log.d("Repository", "New page")
//        return Pager(
//            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
//            pagingSourceFactory = { PagingSource(service) }).flow
//    }
//
//
//    suspend fun selectArticlesWithCategory(category: String): DefaultResponse {
//        val response =
//            NewsService.selectArticlesWithCategory(BuildConfig.NEWS_API_KEY, "kr", category)
//        return if (response.isSuccessful) response.body() as DefaultResponse else DefaultResponse(
//            "fail",
//            "",
//            "client fail",
//            ArrayList()
//        )
//    }
    fun selectArticlesTmp(): Flow<PagingData<Article>> {
        return Pager(PagingConfig(20)) {
            PagingSource(service)
        }.flow
    }

    companion object {
        private var instance: NewsRepositoryImplTmp? = null
        fun getInstance(application: Application, service: NewsService): NewsRepositoryImplTmp? {
            if (instance == null) {
                instance = NewsRepositoryImplTmp(application, service)
            }
            return instance
        }
    }

}