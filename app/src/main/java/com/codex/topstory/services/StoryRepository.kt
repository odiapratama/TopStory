package com.codex.topstory.services

import com.codex.topstory.models.Item
import com.codex.topstory.models.User

interface StoryRepository {
    val listFavorite: ArrayList<Item>?
    val listStory: ArrayList<Item>?
    fun getItem(id: String, listener: StoryListener<Item>)
    fun getUser(id: String, listener: StoryListener<User>)
    fun getTopStory(listener: StoryListener<List<Long>>)
    fun addFavorite(story: Item?)
    fun deleteFavorite(id: Long?)
    fun addListStory(story: Item?)
    fun updateStory(story: Item?)
}

interface StoryListener<T> {
    fun onSuccess(response: T? = null)
    fun onFailed(message: String?)
}