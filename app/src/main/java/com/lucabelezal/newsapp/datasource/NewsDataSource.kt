package com.lucabelezal.newsapp.datasource

import android.content.Context
import com.lucabelezal.newsapp.db.ArticleDatabase
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.network.RetrofitInstance
import com.lucabelezal.newsapp.presenter.favorites.FavoriteView
import com.lucabelezal.newsapp.presenter.news.News
import com.lucabelezal.newsapp.presenter.search.Search
import com.lucabelezal.newsapp.repository.NewsRepository
import kotlinx.coroutines.*

class NewsDataSource(context: Context) {

    private var database: ArticleDatabase = ArticleDatabase(context)
    private var repository: NewsRepository = NewsRepository(database)

    fun getBreakingNews(completion: News.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews(countryCode = "br")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    completion.onSuccess(newsResponse)
                }
                completion.onComplete()
            } else {
                completion.onError(response.message())
                completion.onComplete()
            }
        }
    }

    fun getSearchNews(term: String, completion: Search.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getSearchNews(term)
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    completion.onSuccess(newsResponse)
                }
                completion.onComplete()
            } else {
                completion.onError(response.message())
                completion.onComplete()
            }
        }
    }

    fun saveArticle(article: Article) {
        GlobalScope.launch(Dispatchers.Main) {
            repository.updateInsert(article)
        }
    }

    fun fetchAllArticle(completion: FavoriteView.Presenter) {
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = repository.getAll()

            withContext(Dispatchers.Main) {
                completion.showArticles(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?) {
        GlobalScope.launch(Dispatchers.Main) {
            article?.let { articleDeleted ->
                repository.delete(articleDeleted)
            }
        }
    }
}