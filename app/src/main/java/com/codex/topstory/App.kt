package com.codex.topstory

import android.app.Application
import com.codex.topstory.modules.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(serviceModule))
            properties(
                mapOf("story_api" to "https://hacker-news.firebaseio.com/v0/")
            )
        }
    }
}