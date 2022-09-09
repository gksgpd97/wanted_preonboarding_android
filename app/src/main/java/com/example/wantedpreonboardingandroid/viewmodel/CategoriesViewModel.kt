package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wantedpreonboardingandroid.data.DefaultResponse
import com.example.wantedpreonboardingandroid.data.NewsRepository
import kotlinx.coroutines.launch

class CategoriesViewModel(repository: NewsRepository, category: String) : ViewModel() {

    private val _response = MutableLiveData<DefaultResponse>()
    val response: MutableLiveData<DefaultResponse>
        get() = _response

    init {
        viewModelScope.launch {
            _response.value = repository.selectArticlesWithCategory(category)
        }
    }

    class Factory(private val application: Application, private val category: String) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoriesViewModel(NewsRepository.getInstance(application)!!, category) as T
        }
    }
}