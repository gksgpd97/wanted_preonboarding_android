package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.data.SavedRepository

class NewsDetailViewModel(private val repository: SavedRepository) : ViewModel() {

    fun insert(article: Article) {
        repository.insert(article)
    }

    fun delete(url: String) {
        repository.delete(url)
    }

    fun isSaved(url: String): Boolean {
        return repository.isSaved(url)
    }

    class Factory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsDetailViewModel(SavedRepository.getInstance(application)!!) as T
        }
    }
}