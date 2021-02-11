package com.lucabelezal.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucabelezal.newsapp.R

class ArticleActivity: AbstractActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_article
    }

    override fun onInject() {}
}