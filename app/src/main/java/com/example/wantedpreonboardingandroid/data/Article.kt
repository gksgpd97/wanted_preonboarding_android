package com.example.wantedpreonboardingandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    val url: String,
    val title: String?,
    val author: String?,
    val publishedAt: String?,
    val urlToImage: String?,
    val description: String?
) : Serializable