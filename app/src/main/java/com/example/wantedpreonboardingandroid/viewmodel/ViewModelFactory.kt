//package com.example.wantedpreonboardingandroid.viewmodel
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.wantedpreonboardingandroid.api.NewsService
//import com.example.wantedpreonboardingandroid.data.NewsRepository
//
//class ViewModelFactory constructor(private val context: Context) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        modelClass.isAssignableFrom(TopNewsViewModel::class.java) -> {
//            val repository =
//                NewsRepository(NewsService)
//            TopNewsViewModel(repository) as T
//        }
//        modelClass.isAssignableFrom(TopNewsViewModel::class.java) -> {
//            val repository =
//                TopNewsRepository(TopNewsRemoteDataSource(ServiceLocator.provideApiClient()))
//            TopNewsViewModel(repository) as T
//        }
//        modelClass.isAssignableFrom(NewsDetailViewModel::class.java) -> {
//            NewsDetailViewModel(ServiceLocator.provideSavedRepository(context)) as T
//        }
//        modelClass.isAssignableFrom(SavedViewModel::class.java) -> {
//            SavedViewModel(ServiceLocator.provideSavedRepository(context)) as T
//        }
//        else -> {
//            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
//        }
//    }
//}