package com.codex.topstory.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.codex.topstory.R
import com.codex.topstory.adapters.FavoriteAdapter
import com.codex.topstory.adapters.StoryAdapter
import com.codex.topstory.models.Item
import com.codex.topstory.services.StoryListener
import com.codex.topstory.services.StoryRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STORY = "extra_story"
    }

    private lateinit var storyAdapter: StoryAdapter
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val storyRepository: StoryRepository by inject()
    private var listTopStory: List<Long> = emptyList()
    private lateinit var storyListener: StoryAdapter.OnItemClickListener
    private lateinit var favoriteListener: FavoriteAdapter.OnItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
        initView()
        initData()
    }

    override fun onResume() {
        super.onResume()
        favoriteAdapter.updateData(storyRepository.listFavorite)
        storyAdapter.updateData(storyRepository.listStory)
    }

    private fun initListener() {
        storyListener = object : StoryAdapter.OnItemClickListener {
            override fun onClick(item: Item) {
                val intent = Intent(this@MainActivity, DetailStoryActivity::class.java)
                intent.putExtra(EXTRA_STORY, item)
                startActivity(intent)
            }
        }
        favoriteListener = object : FavoriteAdapter.OnItemClickListener {
            override fun onClick(story: Item) {
                val intent = Intent(this@MainActivity, DetailStoryActivity::class.java)
                intent.putExtra(EXTRA_STORY, story)
                startActivity(intent)
            }
        }
    }

    private fun initView() {
        storyAdapter = StoryAdapter(emptyList(), this, storyListener)
        rvStory.adapter = storyAdapter
        favoriteAdapter = FavoriteAdapter(emptyList(), favoriteListener)
        rvFavStory.adapter = favoriteAdapter
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
        runOnUiThread {
            storyRepository.getItem(story.toString(), object : StoryListener<Item> {
                override fun onSuccess(response: Item?) {
                    storyRepository.addListStory(response ?: Item())
                    pbLoading.progress++
                    if (storyRepository.listStory?.size!! >= 500) pbLoading.visibility = View.GONE
                    storyAdapter.updateData(storyRepository.listStory)
                }

                override fun onFailed(message: String?) {}
            })
        }
    }
}
