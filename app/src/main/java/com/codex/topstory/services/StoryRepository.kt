package com.codex.topstory.services

import com.codex.topstory.models.Item
import com.codex.topstory.models.User

interface StoryRepository {
    val listItem: List<Item>?
    fun getItem(id: String, listener: StoryListener<Item>)
    fun getUser(id: String, listener: StoryListener<User>)
    fun getTopStory(listener: StoryListener<List<Long>>)
}

interface StoryListener<T> {
    fun onSuccess(response: T? = null)
    fun onFailed(message: String?)
}