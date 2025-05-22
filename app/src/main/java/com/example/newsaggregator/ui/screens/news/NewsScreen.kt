package com.example.newsaggregator.ui.screens.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        newsViewModel.loadNews()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        NewsScreenContent(
            padding = padding,
            navHostController = navHostController,
            newsViewModel = newsViewModel,
        )
    }
}