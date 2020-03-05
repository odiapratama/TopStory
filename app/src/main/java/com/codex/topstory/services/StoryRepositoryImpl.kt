import com.codex.topstory.models.Story
import com.codex.topstory.services.StoryApi
import com.codex.topstory.services.StoryListener
import com.codex.topstory.services.StoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryRepositoryImpl(private val storyApi: StoryApi) : StoryRepository {

    private val _storyTrending: List<Story>? = null
    override val listStory: List<Story>?
        get() = _storyTrending

    override fun getStory(id: String, listener: StoryListener<Story>) {
        storyApi.getStory(id).enqueue(object : Callback<Story> {
            override fun onFailure(call: Call<Story>, t: Throwable) {
                listener.onFailed(t.message)
            }

            override fun onResponse(call: Call<Story>, response: Response<Story>) {
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