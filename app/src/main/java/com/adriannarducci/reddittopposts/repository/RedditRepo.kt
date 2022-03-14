package com.adriannarducci.reddittopposts.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adriannarducci.reddittopposts.database.RedditDatabase
import com.adriannarducci.reddittopposts.models.RedditPost
import com.adriannarducci.reddittopposts.networking.RedditClient
import com.adriannarducci.reddittopposts.networking.RedditService
import kotlinx.coroutines.flow.Flow

class RedditRepo(context: Context) {
    private val redditService = RedditClient.getClient().create(RedditService::class.java)
    private val redditDatabase = RedditDatabase.create(context)

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            PagingConfig(pageSize = 40, enablePlaceholders = false, prefetchDistance = 3),
            remoteMediator = RedditRemoteMediator(redditService, redditDatabase),
            pagingSourceFactory = { redditDatabase.redditPostsDao().getPosts() }
        ).flow
    }
}