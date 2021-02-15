package com.lucabelezal.newsapp.utils

import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import com.lucabelezal.newsapp.utils.Environment.Companion.SEARCH_NEWS_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class QueryTextListener(
    lifecycle: Lifecycle,
    private val queryTextListener: (String?) -> Unit
): SearchView.OnQueryTextListener, LifecycleObserver {

    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                    delay(SEARCH_NEWS_DELAY)
                    queryTextListener(newText)
                }
        }
        return false
    }

}