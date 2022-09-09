package com.example.wantedpreonboardingandroid.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert
    fun insertArticle(article: Article)

    @Query("SELECT * FROM articles ORDER BY id DESC")
    fun getAll(): LiveData<MutableList<Article>>

    @Query("DELETE FROM articles WHERE id = :id")
    fun deleteArticle(id: Int)
}