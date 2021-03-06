import com.codex.topstory.models.Item
import com.codex.topstory.models.User
import com.codex.topstory.services.StoryApi
import com.codex.topstory.services.StoryListener
import com.codex.topstory.services.StoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryRepositoryImpl(private val storyApi: StoryApi) : StoryRepository {

    private val _listFavorite: ArrayList<Item>? = ArrayList()
    override val listFavorite: ArrayList<Item>?
        get() = _listFavorite

    private val _listStory: ArrayList<Item>? = ArrayList()
    override val listStory: ArrayList<Item>?
        get() = _listStory

    override fun addFavorite(story: Item?) {
        story?.let { _listFavorite?.add(it) }
    }

    override fun deleteFavorite(id: Long?) {
        listFavorite?.removeAll { it.id == id }
    }

    override fun addListStory(story: Item?) {
        story?.let { _listStory?.add(it) }
    }

    override fun updateStory(story: Item?) {
        story?.id?.let { _listStory?.find { it.id == story.id }?.like = story.like }
    }

    override fun getItem(id: String, listener: StoryListener<Item>) {
        storyApi.getItem(id).enqueue(object : Callback<Item> {
            override fun onFailure(call: Call<Item>, t: Throwable) {
                listener.onFailed(t.message)
            }

            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful) {
                    listener.onSuccess(response.body())
                } else {
                    listener.onFailed(response.message())
                }
            }
        })
    }

    override fun getUser(id: String, listener: StoryListener<User>) {
        storyApi.getUser(id).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                listener.onFailed(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    listener.onSuccess(response.body())
                } else {
                    listener.onFailed(response.message())
                }
            }
        })
    }

    override fun getTopStory(listener: StoryListener<List<Long>>) {
        storyApi.getTopStory().enqueue(object : Callback<List<Long>> {
            override fun onFailure(call: Call<List<Long>>, t: Throwable) {
                listener.onFailed(t.message)
            }

            override fun onResponse(call: Call<List<Long>>, response: Response<List<Long>>) {
                if (response.isSuccessful) {
                    listener.onSuccess(response.body())
                } else {
                    listener.onFailed(response.message())
                }
            }
        })
    }
}