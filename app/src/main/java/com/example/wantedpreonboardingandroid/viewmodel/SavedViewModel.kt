package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.data.SavedRepository

class SavedViewModel(private val repository: SavedRepository) : ViewModel() {

    fun getAll(): LiveData<ArrayList<Article>> {
        return repository.getAll() as LiveData<ArrayList<Article>>
    }

    class Factory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SavedViewModel(SavedRepository.getInstance(application)!!) as T
        }
    }
}