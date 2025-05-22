package com.example.newsaggregator.ui.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsaggregator.domain.models.NewsModel
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
            NewsItem(
                newsModel = newsModel,
                onClick = {
                    navHostController.navigate(
                        route = Screen.NewsDetail.route
                    )
                },
            )
        }
    }
}

@Composable
fun NewsItem(
    newsModel: NewsModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(newsModel.title) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(newsModel.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = newsModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = newsModel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}