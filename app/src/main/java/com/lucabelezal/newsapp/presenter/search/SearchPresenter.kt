package com.lucabelezal.newsapp.presenter.search

import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.NewsResponse
import com.lucabelezal.newsapp.presenter.news.NewsView

class SearchPresenter(
    val view: NewsView.View,
    private val dataSource: NewsDataSource
): SearchView.Presenter {

    override fun search(term: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}