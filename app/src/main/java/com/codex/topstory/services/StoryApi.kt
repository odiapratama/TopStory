package com.codex.topstory.services

import com.codex.topstory.models.Item
import com.codex.topstory.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_PATH = ""

interface StoryApi {
    @GET("item/{id}.json")
    fun getItem(
        @Path("id") id: String,
        @Query("print") since: String = "pretty"
    ): Call<Item>

    @GET("user/{id}.json")
    fun getUser(
        @Path("id") id: String,
        @Query("print") since: String = "pretty"
    ): Call<User>

    @GET("topstories.json")
    fun getTopStory(
        @Query("print") since: String = "pretty"
    ): Call<List<Long>>
}