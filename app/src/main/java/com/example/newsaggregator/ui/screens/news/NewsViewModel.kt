package com.example.newsaggregator.ui.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecases.GetNewsFromApiUseCase
import com.example.newsaggregator.domain.usecases.GetNewsFromDbUseCase
import com.example.newsaggregator.domain.usecases.RefreshNewsInDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsFromApiUseCase: GetNewsFromApiUseCase,
    private val getNewsFromDbUseCase: GetNewsFromDbUseCase,
    private val refreshNewsInDbUseCase: RefreshNewsInDbUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _newsScreenState.value = NewsScreenState.Error(throwable = throwable)
    }

    private val _newsScreenState: MutableStateFlow<NewsScreenState> =
        MutableStateFlow(NewsScreenState.Initial)
    val newsScreenState: StateFlow<NewsScreenState> = _newsScreenState.asStateFlow()

    fun loadNews() {
        viewModelScope.launch(exceptionHandler) {

            _newsScreenState.value = NewsScreenState.Loading

            val newsFromDb = getNewsFromDbUseCase.invoke()
            if (newsFromDb.isNotEmpty()) {
                _newsScreenState.value = NewsScreenState.Success(
                    news = newsFromDb,
                    isRefreshing = false,
                )
            }

            val newsFromApi = getNewsFromApiUseCase.invoke()
            _newsScreenState.value = NewsScreenState.Success(
                news = newsFromApi,
                isRefreshing = false,
            )

            if (newsFromApi.isNotEmpty()) {
                refreshNewsInDbUseCase.invoke(news = newsFromApi)
            }
        }
    }

    fun refreshNews() {
        viewModelScope.launch(exceptionHandler) {

            val currentNews = if (newsScreenState.value is NewsScreenState.Success) {
                (newsScreenState.value as NewsScreenState.Success).news
            } else {
                emptyList()
            }

            _newsScreenState.value = NewsScreenState.Success(
                news = currentNews,
                isRefreshing = true,
            )

            val newsFromApi = getNewsFromApiUseCase.invoke()

            _newsScreenState.value = NewsScreenState.Success(
                news = if (newsFromApi.isNotEmpty()) newsFromApi else currentNews,
                isRefreshing = false,
            )

            if (newsFromApi.isNotEmpty()) {
                refreshNewsInDbUseCase.invoke(news = newsFromApi)
            }
        }
    }
}