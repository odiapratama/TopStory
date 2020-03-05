package com.codex.topstory.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codex.topstory.R
import com.codex.topstory.adapters.StoryAdapter
import com.codex.topstory.models.Story
import com.codex.topstory.services.StoryListener
import com.codex.topstory.services.StoryRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var storyAdapter: StoryAdapter
    private val storyRepository: StoryRepository by inject()
    private var listTopStory: List<Long> = emptyList()
    private var listStory: ArrayList<Story> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        storyAdapter = StoryAdapter(emptyList())
        rvStory.adapter = storyAdapter
    }

    private fun initData() {
        storyRepository.getTopStory(object : StoryListener<List<Long>> {
            override fun onSuccess(response: List<Long>?) {
                listTopStory = response ?: emptyList()
                for (story in listTopStory) {
                    getStory(story)
                }
            }

            override fun onFailed(message: String?) {}
        })
    }

    private fun getStory(story: Long) {
        storyRepository.getStory(story.toString(), object : StoryListener<Story> {
            override fun onSuccess(response: Story?) {
                listStory.add(response ?: Story())
                storyAdapter.updateData(listStory)
            }

            override fun onFailed(message: String?) {}
        })
    }
}
