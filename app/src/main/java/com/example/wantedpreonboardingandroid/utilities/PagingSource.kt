package com.example.wantedpreonboardingandroid.utilities

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wantedpreonboardingandroid.BuildConfig
import com.example.wantedpreonboardingandroid.api.NewsService
import com.example.wantedpreonboardingandroid.data.Article

class PagingSource(private val service: NewsService) : PagingSource<Int, Article>() {
    private val pageSize = 20
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = service.selectArticlesTmp(BuildConfig.NEWS_API_KEY, "kr", page, pageSize)
            val articles = response.body()!!.articles
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null  else page -1,
                nextKey = if (articles.isEmpty()) null else page+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}