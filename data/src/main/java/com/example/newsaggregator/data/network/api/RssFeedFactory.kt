package com.example.newsaggregator.data.network.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType
import retrofit2.Retrofit

object RssFeedFactory {

    private const val BASE_URL = "https://www.theguardian.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            XML.asConverterFactory(
                MediaType.get("application/xml; charset=UTF8")
            )
        )
        .build()

    val guardian: RssFeed = retrofit.create(RssFeed::class.java)
}