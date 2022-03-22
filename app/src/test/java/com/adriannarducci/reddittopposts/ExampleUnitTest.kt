package com.adriannarducci.reddittopposts

import androidx.test.core.app.ApplicationProvider
import com.adriannarducci.reddittopposts.repository.RedditRepo
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun repositoryTest() = runBlocking {
        val repo = RedditRepo(ApplicationProvider.getApplicationContext())
        val firstItem = repo.fetchPosts()
        assertTrue(firstItem.count() > 1)
    }
}