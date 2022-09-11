package com.example.wantedpreonboardingandroid.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wantedpreonboardingandroid.api.NewsService
import com.example.wantedpreonboardingandroid.utilities.PagingSource
import javax.inject.Inject

class NewsRepositoryTmp @Inject constructor(private val service: NewsService) {
    fun selectArticlesTmp() = Pager(
        config = PagingConfig(20, maxSize = 100),
        pagingSourceFactory = { PagingSource(service) }
    ).liveData
}