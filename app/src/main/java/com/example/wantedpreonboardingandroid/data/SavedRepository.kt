package com.example.wantedpreonboardingandroid.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

class SavedRepository(application: Application) {
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

    fun delete(id: Int) {
        try {
            val thread = Thread {
                articleDao.deleteArticle(id)
            }
            thread.start()
        } catch (e: Exception) {
            Log.d("SavedRepository", e.toString())
        }
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