package com.adriannarducci.reddittopposts.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.adriannarducci.reddittopposts.models.RedditPost
import com.adriannarducci.reddittopposts.repository.RedditRepo
import kotlinx.coroutines.flow.Flow

class RedditViewModel(application: Application) : AndroidViewModel(application) {
    private val redditRepo = RedditRepo(application)

    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return redditRepo.fetchPosts().cachedIn(viewModelScope)
    }
}