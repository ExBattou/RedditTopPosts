package com.adriannarducci.reddittopposts

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import com.adriannarducci.reddittopposts.database.RedditDatabase
import com.adriannarducci.reddittopposts.models.RedditPost
import com.adriannarducci.reddittopposts.networking.RedditClient
import com.adriannarducci.reddittopposts.repository.RedditRemoteMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteMediatorTest {
    private val mockDb = RedditDatabase.create(ApplicationProvider.getApplicationContext())
    private val mockApi = RedditClient.getClient().create(FakeRedditApi::class.java)

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        val remoteMediator = RedditRemoteMediator(mockApi , mockDb)
        val pagingState = PagingState<Int,RedditPost> (
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached )
    }
}