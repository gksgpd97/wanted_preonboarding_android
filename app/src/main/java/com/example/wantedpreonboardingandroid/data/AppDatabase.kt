package com.example.wantedpreonboardingandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "articles.db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}