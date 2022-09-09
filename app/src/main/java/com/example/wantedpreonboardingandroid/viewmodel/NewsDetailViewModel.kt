package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.data.SavedRepository

class NewsDetailViewModel(private val repository: SavedRepository) : ViewModel() {

    fun insert(article: Article) {
        return repository.insert(article)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    class Factory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsDetailViewModel(SavedRepository.getInstance(application)!!) as T
        }
    }
}