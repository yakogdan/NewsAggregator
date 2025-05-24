package com.example.newsaggregator.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsaggregator.data.database.models.NewsDBO.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NewsDBO(

    @PrimaryKey
    @ColumnInfo(name = "url")
    val newsUrl: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "pub_date")
    val pubDate: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
) {
    companion object {
        const val TABLE_NAME = "news_table"
    }
}