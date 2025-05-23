package com.example.newsaggregator.ui.screens.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsaggregator.ui.navigation.Screen

@Composable
fun NewsScreenContent(
    padding: PaddingValues,
    navHostController: NavHostController,
    newsViewModel: NewsViewModel,
) {
    val newsScreenState by newsViewModel.newsScreenState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ) {
        when (val state = newsScreenState) {

            NewsScreenState.Initial -> {}

            NewsScreenState.Loading -> {
                LoadingState()
            }

            is NewsScreenState.Error -> {
                ErrorState(message = state.throwable.message.toString())
            }

            is NewsScreenState.Success -> {
                SuccessState(
                    state = state,
                    navHostController = navHostController,
                )
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun SuccessState(
    state: NewsScreenState.Success,
    navHostController: NavHostController,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            items = state.news,
            key = { it.title }
        ) { newsModel ->
            NewsCard(
                newsModel = newsModel,
                onClick = {
                    navHostController.navigate(
                        route = Screen.NewsDetail.getRouteWithArgs(newsModel.newsUrl)
                    )
                },
            )
        }
    }
}