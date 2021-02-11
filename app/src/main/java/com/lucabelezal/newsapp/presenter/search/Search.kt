package com.lucabelezal.newsapp.presenter.search

import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.model.NewsResponse

interface Search {
    interface Presenter {
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}