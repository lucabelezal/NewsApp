package com.lucabelezal.newsapp.datasource

import com.lucabelezal.newsapp.network.RetrofitInstance
import com.lucabelezal.newsapp.presenter.news.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {
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
}