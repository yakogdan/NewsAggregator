package com.example.newsaggregator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsaggregator.data.database.dao.NewsDao
import com.example.newsaggregator.data.database.models.NewsDBO

@Database(
    entities = [
        NewsDBO::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        private const val DB_NAME = "news_db"

        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DB_NAME,
            ).build()
    }
}