package com.codex.topstory.services

import com.codex.topstory.models.Story
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_PATH = ""

interface StoryApi {
    @GET("item/{id}.json")
    fun getStory(
        @Path("id") id: String,
        @Query("print") since: String = "pretty"
    ): Call<Story>

    @GET("topstories.json")
    fun getTopStory(
        @Query("print") since: String = "pretty"
    ): Call<List<Long>>
}