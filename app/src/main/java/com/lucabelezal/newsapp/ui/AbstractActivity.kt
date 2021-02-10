package com.lucabelezal.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayout()
        onInject()
    }
    protected abstract fun getLayout(): Int
    protected abstract fun onInject()
}