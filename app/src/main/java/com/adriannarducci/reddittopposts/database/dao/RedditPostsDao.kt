package com.adriannarducci.reddittopposts.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adriannarducci.reddittopposts.models.RedditPost

@Dao
interface RedditPostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(redditPosts: List<RedditPost>)

    @Query("SELECT * FROM redditPosts")
    fun getPosts(): PagingSource<Int, RedditPost>


}