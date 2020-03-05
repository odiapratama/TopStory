package com.codex.topstory.services
import com.codex.topstory.models.Story

interface StoryRepository {
    val listStory: List<Story>?
    fun getStory(listener: StoryListener<Story>, id: String = "")
}

interface StoryListener<T> {
    fun onSuccess(response: T? = null)
    fun onFailed(message: String?)
}