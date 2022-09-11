package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.data.SavedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(private val repository: SavedRepository) : ViewModel() {

    fun insert(article: Article) {
        repository.insert(article)
    }

    fun delete(url: String) {
        repository.delete(url)
    }

    fun isSaved(url: String): Boolean {
        return repository.isSaved(url)
    }

}