package com.lucabelezal.newsapp.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucabelezal.newsapp.R
import com.lucabelezal.newsapp.adapter.NewsAdapter
import com.lucabelezal.newsapp.databinding.ActivityNewsBinding
import com.lucabelezal.newsapp.datasource.NewsDataSource
import com.lucabelezal.newsapp.model.Article
import com.lucabelezal.newsapp.presenter.news.News
import com.lucabelezal.newsapp.presenter.news.NewsPresenter
import com.lucabelezal.newsapp.ui.article.ArticleActivity
import com.lucabelezal.newsapp.ui.favorites.FavoritesActivity
import com.lucabelezal.newsapp.ui.search.SearchActivity

class NewsActivity: AppCompatActivity(), News.View  {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var presenter: NewsPresenter

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataSource = NewsDataSource(this)
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        setupRecycle()
        clickAdapter()
    }

    override fun hideProgressBar() {
        binding.rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showProgressBar() {
        binding.rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showArticles(articles: List<Article>) {
        newsAdapter.differ.submitList(articles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_menu -> {
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.favorite_menu -> {
                Intent(this, FavoritesActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clickAdapter() {
        newsAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    private fun setupRecycle() {
        with(binding.rvNews) {
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