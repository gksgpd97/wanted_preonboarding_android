package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wantedpreonboardingandroid.data.DefaultResponse
import com.example.wantedpreonboardingandroid.data.NewsRepository
import kotlinx.coroutines.launch

class TopNewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _response = MutableLiveData<DefaultResponse>()
    val response: MutableLiveData<DefaultResponse>
        get() = _response

    init {
        viewModelScope.launch {
            _response.value = repository.selectArticles()
        }
    }

    class Factory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TopNewsViewModel(NewsRepository.getInstance(application)!!) as T
        }
    }
}