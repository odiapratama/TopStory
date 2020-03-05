package com.codex.topstory.services

import com.codex.topstory.models.Story
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_PATH = "item/"

interface StoryApi {
    @GET("{id}.json")
    fun getStory(
        @Path("language") language: String,
        @Query("print") since: String = "pretty"
    ): Call<Story>
}