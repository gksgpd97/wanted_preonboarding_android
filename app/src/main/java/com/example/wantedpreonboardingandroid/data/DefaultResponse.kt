package com.example.wantedpreonboardingandroid.data

data class DefaultResponse(
    val status: String,
    val code: String,
    val message: String,
    val articles: ArrayList<Article>
)