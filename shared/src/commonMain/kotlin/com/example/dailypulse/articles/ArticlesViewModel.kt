package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState>
        get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchesArticles = fetchArticles()

            delay(500)

            _articlesState.emit(ArticlesState(articles = fetchesArticles))
        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles()

    private fun mockArticles(): List<Article> {
        val articleList = mutableListOf<Article>()

        articleList.add(
            Article(
                title = "Sample Article 1",
                desc = "This is a sample article 1 description.",
                date = "2024-04-06",
                imageUrl = "https://pin.it/2Zq7pgWBI"
            )
        )

        articleList.add(
            Article(
                title = "Sample Article 2",
                desc = "This is a sample article 2 description.",
                date = "2024-04-05",
                imageUrl = "https://pin.it/697JjLFEu"
            )
        )

        return articleList
    }

}