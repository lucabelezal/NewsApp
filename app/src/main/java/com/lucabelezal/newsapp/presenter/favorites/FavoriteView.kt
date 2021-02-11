package com.lucabelezal.newsapp.presenter.favorites

import com.lucabelezal.newsapp.model.Article

interface FavoriteView {
    interface Presenter {
        fun showArticles(articles: List<Article>)
    }
}