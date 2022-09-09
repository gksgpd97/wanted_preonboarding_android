package com.example.wantedpreonboardingandroid.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert
    fun insertArticle(article: Article)

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun getAll(): LiveData<MutableList<Article>>

    @Query("DELETE FROM articles WHERE url = :url")
    fun deleteArticle(url: String)

    @Query("SELECT COUNT(url) FROM articles WHERE url = :url")
    fun isSaved(url: String): Int

    @Query("SELECT COUNT(*) FROM articles")
    fun haveSaved(): Int
}