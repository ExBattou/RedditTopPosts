package com.adriannarducci.reddittopposts.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adriannarducci.reddittopposts.models.RedditPost
import com.adriannarducci.reddittopposts.networking.RedditService
import retrofit2.HttpException
import java.io.IOException

class RedditPagingSource(private val redditService: RedditService) :
    PagingSource<String, RedditPost>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val response = redditService.fetchPosts(loadSize = params.loadSize)
            val listing = response.body()?.data
            val redditPosts = listing?.children?.map { it.data }
            LoadResult.Page(
                redditPosts ?: listOf(),
                listing?.before,
                listing?.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true
    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        TODO("Not yet implemented")
        // I left this here otherwise it would crash, since the new version
        // of Paging needs the getRefreshKey Implementation, and it's day 3 :D
    }
}