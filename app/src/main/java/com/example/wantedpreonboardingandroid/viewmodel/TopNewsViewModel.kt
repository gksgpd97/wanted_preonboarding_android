package com.example.wantedpreonboardingandroid.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wantedpreonboardingandroid.data.DefaultResponse
import com.example.wantedpreonboardingandroid.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    private val _response = MutableLiveData<DefaultResponse>()
    val response: MutableLiveData<DefaultResponse>
        get() = _response

    init {
        viewModelScope.launch {
            _response.value = repository.selectArticles()
        }
    }


}