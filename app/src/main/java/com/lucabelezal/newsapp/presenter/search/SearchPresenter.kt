package com.lucabelezal.newsapp.presenter.search

import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.NewsResponse
import com.lucabelezal.newsapp.presenter.news.News

class SearchPresenter(
    val view: News.View,
    private val dataSource: NewsDataSource
): Search.Presenter {

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