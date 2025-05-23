package com.example.newsaggregator.ui.screens.newsdetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewsDetailScreen(
    newsUrl: String?,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        NewsDetailScreenContent(
            newsUrl = newsUrl,
            padding = padding,
        )
    }
}