package com.lucabelezal.newsapp.ui.search

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.adapter.NewsAdapter
import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.presenter.news.News
import com.lucabelezal.newsapp.presenter.search.SearchPresenter
import com.lucabelezal.newsapp.ui.AbstractActivity
import com.lucabelezal.newsapp.ui.article.ArticleActivity
import com.lucabelezal.newsapp.utils.QueryTextListener
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), News.View {
    private lateinit var presenter: SearchPresenter

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = SearchPresenter(this, dataSource)
        setupRecycle()
        search()
        clickAdapter()
    }

    override fun hideProgressBar() {
        rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showProgressBar() {
        rvProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showArticles(articles: List<Article>) {
        newsAdapter.differ.submitList(articles.toList())
    }

    private fun setupRecycle() {
        with(rvNewsSearch) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun clickAdapter() {
        newsAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    private fun search() {
        searchNews.setOnQueryTextListener(
            QueryTextListener(this.lifecycle) { newText ->
                newText?.let { query ->
                        if (query.isNotEmpty()) {
                            presenter.search(query)
                            rvProgressBarSearch.visibility = View.VISIBLE
                        }
                }
            }
        )
    }
}