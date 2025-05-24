package com.example.newsaggregator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsaggregator.data.database.models.NewsDBO
import com.example.newsaggregator.data.database.models.NewsDBO.Companion.TABLE_NAME

@Dao
interface NewsDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAll(): List<NewsDBO>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<NewsDBO>)

    @Transaction
    suspend fun refresh(items: List<NewsDBO>) {
        clear()
        insertAll(items)
    }
}