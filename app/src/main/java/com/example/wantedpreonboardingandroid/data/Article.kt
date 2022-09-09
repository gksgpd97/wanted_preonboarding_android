package com.example.wantedpreonboardingandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String?,
    val author: String?,
    val publishedAt: String?,
    val urlToImage: String?,
    val url: String?,
    val description: String?
) : Serializable