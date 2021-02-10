package com.lucabelezal.newsapp.presenter.news

import com.lucabelezal.newsapp.model.NewsResponse
import com.lucabelezal.newsapp.model.data.NewsDataSource

class NewsPresenter(
    val view: NewsView.View,
    private val dataSource: NewsDataSource
    ): NewsView.Presenter {

    override fun requestAll() {
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
