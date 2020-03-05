package com.codex.topstory.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codex.topstory.R
import com.codex.topstory.adapters.StoryAdapter
import com.codex.topstory.services.StoryRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var storyAdapter: StoryAdapter
    private val storyRepository: StoryRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        storyAdapter = StoryAdapter(emptyList())
        rvStory.adapter = storyAdapter
    }

    private fun initData() {
//        storyRepository.getStory()
    }
}
