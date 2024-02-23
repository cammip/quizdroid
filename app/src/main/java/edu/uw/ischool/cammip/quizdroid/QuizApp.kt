package edu.uw.ischool.cammip.quizdroid
import android.app.Application
import android.util.Log
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat.getSystemService
import org.json.JSONArray
import java.io.IOException

data class Question(
    val question: String,
    val answers: List<String>,
    val answer: Int
)

data class Topic(
    val title: String,
    val desc: String,
    val questions: List<Question>
)

interface TopicRepository {
    fun getTopics(): List<Topic>
}

class useTopicRepo(private val context: Context): TopicRepository {

    override fun getTopics(): List<Topic> {
        val topics = mutableListOf<Topic>()

        try {
            val jsonString = loadJSONFromAsset(context)
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val title = jsonObject.getString("title")
                val desc = jsonObject.getString("desc")
                val questionsArray = jsonObject.getJSONArray("questions")
                val questions = mutableListOf<Question>()

                for (j in 0 until questionsArray.length()) {
                    val questionObject = questionsArray.getJSONObject(j)

                    val text = questionObject.getString("question")
                    val answer = questionObject.getInt("answer")
                    val answersArray = questionObject.getJSONArray("answers")
                    val answerOptions = mutableListOf<String>()

                    for (k in 0 until answersArray.length()) {
                        answerOptions.add(answersArray.getString(k))
                    }

                    questions.add(Question(text, answerOptions, answer))
                }

                topics.add(Topic(title, desc, questions))
            }
        } catch (e: Exception) {
            Log.d("noo1", e.toString())
        }

        return topics
    }

    private fun loadJSONFromAsset(context: Context): String {
        lateinit var jsonTopics: String
        try {
            jsonTopics = context.assets.open("questionsfile.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("noo2", "error")
        }
        //Log.d("string", jsonTopics)
        return jsonTopics
    }
}

class QuizApp : Application() {
    lateinit var topicRepository: TopicRepository
    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp started")

        topicRepository = useTopicRepo(applicationContext)
    }
}