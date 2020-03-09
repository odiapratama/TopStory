package com.codex.topstory.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codex.topstory.R
import com.codex.topstory.activities.MainActivity.Companion.EXTRA_STORY
import com.codex.topstory.adapters.CommentAdapter
import com.codex.topstory.models.Item
import com.codex.topstory.models.User
import com.codex.topstory.services.StoryListener
import com.codex.topstory.services.StoryRepository
import com.codex.topstory.utils.setTextHtml
import com.codex.topstory.utils.timeToDate
import kotlinx.android.synthetic.main.activity_detail_story.*
import org.koin.android.ext.android.inject

class DetailStoryActivity : AppCompatActivity() {

    private var item: Item? = null
    private val storyRepository: StoryRepository by inject()
    private val commentAdapter = CommentAdapter(emptyList())
    private val listComment = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)
        item = intent.getSerializableExtra(EXTRA_STORY) as Item
        initListener()
        initData()
        initView()
    }

    private fun initListener() {
        ivFav.setOnClickListener {
            if (item?.like == true) {
                ivFav.setBackgroundResource(R.drawable.unstar)
                item?.like = false
                item?.id?.let { storyRepository.deleteFavorite(it) }
            } else {
                ivFav.setBackgroundResource(R.drawable.star)
                item?.like = true
                item?.let { storyRepository.addFavorite(it) }
            }
            item?.let { storyRepository.updateStory(it) }
        }
    }

    private fun initData() {
        storyRepository.getUser(item?.by ?: "", object : StoryListener<User> {
            override fun onSuccess(response: User?) {
                tvDescription.setTextHtml(response?.about)
            }

            override fun onFailed(message: String?) {}
        })
        item?.kids?.let {
            for (id in it) {
                getItem(id)
            }
        }
    }

    private fun initView() {
        rvComment.adapter = commentAdapter
        tvTitle.text = item?.title
        tvAuthor.text = item?.by
        tvDate.text = item?.time?.timeToDate()
        if (item?.like == true) ivFav.setBackgroundResource(R.drawable.star)
    }

    private fun getItem(id: Long) {
        runOnUiThread {
            storyRepository.getItem(id.toString(), object : StoryListener<Item> {
                override fun onSuccess(response: Item?) {
                    response?.let { listComment.add(it) }
                    commentAdapter.updateData(listComment)
                }

                override fun onFailed(message: String?) {}
            })
        }
    }
}
