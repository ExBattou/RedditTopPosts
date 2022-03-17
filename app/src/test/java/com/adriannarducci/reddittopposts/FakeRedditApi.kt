package com.adriannarducci.reddittopposts

import com.adriannarducci.reddittopposts.models.RedditApiResponse
import com.adriannarducci.reddittopposts.models.RedditListing
import com.adriannarducci.reddittopposts.networking.RedditService
import retrofit2.Response

class FakeRedditApi : RedditService {
    override suspend fun fetchPosts(loadSize: Int, after: String?, before: String?): Response<RedditApiResponse> {
        val listing = RedditListing(listOf(), null,null)
        val response = Response.success(RedditApiResponse(listing))
        return response
    }
}