package com.example.newsaggregator.ui.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsaggregator.R
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
                ErrorState(
                    message = state.throwable.message.toString(),
                    onButtonClick = { newsViewModel.loadNews() },
                )
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

@Composable
private fun ErrorState(
    message: String,
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = message.toLoadingError(),
                color = MaterialTheme.colorScheme.error,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { onButtonClick() }
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.retry),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
private fun String.toLoadingError() =
    LocalContext.current.getString(R.string.error_while_loading_news, this)