package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wantedpreonboardingandroid.api.NewsService
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.data.NewsRepositoryImplTmp
import com.example.wantedpreonboardingandroid.data.NewsRepositoryTmp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModelTmp @Inject constructor(private val newsRepositoryTmp: NewsRepositoryTmp) :
    ViewModel() {

        val response = newsRepositoryTmp.selectArticlesTmp().cachedIn(viewModelScope)


    //    private val _response = MutableLiveData<PagingData<Article>>()
//
//    suspend fun selectArticlesTmp():LiveData<PagingData<Article>>{
//        val response = repository.selectArticlesTmp().cachedIn(viewModelScope)
//        _response.value = response.value
//        return response
//    }
//
////    val response = _response.switchMap {
////        repository.selectArticlesTmp().cachedIn(viewModelScope)
////    }
//
//    fun selectArticlesTmp2() : Flow<PagingData<Article>>{
//        return repository.selectArticlesTmp2().cachedIn(viewModelScope)
//    }
}