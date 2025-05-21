package com.example.newsaggregator.ui.screens.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecases.GetNewsFromApiUseCase
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
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _newsScreenState.value = NewsScreenState.Error(throwable = throwable)
        Log.e("myExceptionHandler", throwable.message.toString())
    }

    private val _newsScreenState: MutableStateFlow<NewsScreenState> =
        MutableStateFlow(NewsScreenState.Initial)
    val newsScreenState: StateFlow<NewsScreenState> = _newsScreenState.asStateFlow()

    fun loadNews() {
        viewModelScope.launch(exceptionHandler) {
            _newsScreenState.value = NewsScreenState.Loading
            _newsScreenState.value = NewsScreenState.Success(news = getNewsFromApiUseCase.invoke())
        }
    }
}