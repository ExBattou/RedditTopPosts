package com.adriannarducci.reddittopposts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adriannarducci.reddittopposts.database.dao.RedditKeysDao
import com.adriannarducci.reddittopposts.database.dao.RedditPostsDao
import com.adriannarducci.reddittopposts.models.RedditKeys
import com.adriannarducci.reddittopposts.models.RedditPost

@Database(
    entities = [RedditPost::class, RedditKeys::class],
    version = 1,
    exportSchema = false
)
abstract class RedditDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): RedditDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, RedditDatabase::class.java, "redditclone.db")
            return databaseBuilder.build()
        }
    }

    abstract fun redditPostsDao(): RedditPostsDao
    abstract fun redditKeysDao(): RedditKeysDao
}