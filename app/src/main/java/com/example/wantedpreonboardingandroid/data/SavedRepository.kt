package com.example.wantedpreonboardingandroid.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import javax.inject.Inject

class SavedRepository @Inject constructor(application: Application) {
    private val appDatabase = AppDatabase.getInstance(application)!!
    private val articleDao = appDatabase.articleDao()
    private val articles = articleDao.getAll()

    fun getAll(): LiveData<MutableList<Article>> {
        return articles
    }

    fun insert(article: Article) {
        try {
            val thread = Thread {
                articleDao.insertArticle(article)
            }
            thread.start()
        } catch (e: Exception) {
            Log.d("SavedRepository", e.toString())
        }
    }

    fun delete(url: String) {
        try {
            val thread = Thread {
                articleDao.deleteArticle(url)
            }
            thread.start()
        } catch (e: Exception) {
            Log.d("SavedRepository", e.toString())
        }
    }

    fun isSaved(url: String): Boolean {
        return articleDao.isSaved(url) == 1
    }

    fun haveSaved(): Boolean{
        return articleDao.haveSaved() >= 1
    }

    companion object {
        private var instance: SavedRepository? = null
        fun getInstance(application: Application): SavedRepository? {
            if (instance == null) {
                instance = SavedRepository(application)
            }
            return instance
        }
    }

}