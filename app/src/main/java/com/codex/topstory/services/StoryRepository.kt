package com.codex.topstory.services

import com.codex.topstory.models.Story

interface StoryRepository {
    val listStory: List<Story>?
    fun getStory(id: String, listener: StoryListener<Story>)
    fun getTopStory(listener: StoryListener<List<Long>>)
}

interface StoryListener<T> {
    fun onSuccess(response: T? = null)
    fun onFailed(message: String?)
}