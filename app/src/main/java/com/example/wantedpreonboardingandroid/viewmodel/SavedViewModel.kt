package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.data.SavedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val repository: SavedRepository) : ViewModel() {

    fun getAll(): LiveData<ArrayList<Article>> {
        return repository.getAll() as LiveData<ArrayList<Article>>
    }

    fun haveSaved(): Boolean {
        return repository.haveSaved()
    }

}