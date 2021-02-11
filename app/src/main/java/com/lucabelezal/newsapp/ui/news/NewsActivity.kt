package com.lucabelezal.newsapp.ui.news

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.adapter.NewsAdapter
import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.presenter.news.NewsPresenter
import com.lucabelezal.newsapp.presenter.news.News
import com.lucabelezal.newsapp.ui.AbstractActivity
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity: AbstractActivity(), News.View  {
    private lateinit var presenter: NewsPresenter

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun getLayout(): Int = R.layout.activity_news

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        setupRecycle()
    }

    override fun hideProgressBar() {
        rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showArticles(articles: List<Article>) {
        newsAdapter.differ.submitList(articles.toList())
    }

    private fun setupRecycle() {
        with(rvNews) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@NewsActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}