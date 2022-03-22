package com.adriannarducci.reddittopposts

import com.adriannarducci.reddittopposts.ui.RedditAdapter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

class AdapterTest {

    var redditAdapter = RedditAdapter()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testItemsAddedToLayout() {
        redditAdapter = spy(RedditAdapter())
        doNothing().`when`(redditAdapter).itemCount.compareTo(0)
    }
}