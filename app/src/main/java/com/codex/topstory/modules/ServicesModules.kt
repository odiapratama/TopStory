package com.codex.topstory.modules

import StoryRepositoryImpl
import com.codex.topstory.services.API_PATH
import com.codex.topstory.services.StoryApi
import com.codex.topstory.services.StoryRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val serviceModule = module {
    single { createOkHttpClient() }
    single { createApi<StoryApi>(API_PATH, get(), getProperty("story_api")) }
    single { StoryRepositoryImpl(get()) as StoryRepository }
}

private fun createOkHttpClient(): OkHttpClient {
    val timeout = 10L
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()
}

private inline fun <reified T> createApi(
    servicePath: String,
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl + servicePath)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}