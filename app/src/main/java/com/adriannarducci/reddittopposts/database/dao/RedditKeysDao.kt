package com.adriannarducci.reddittopposts.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adriannarducci.reddittopposts.models.RedditKeys

@Dao
interface RedditKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRedditKeys(redditKey: RedditKeys)

    @Query("SELECT * FROM redditKeys ORDER BY id DESC")
    suspend fun getRedditKeys(): List<RedditKeys>

}