package edu.uw.ischool.cammip.quizdroid
import android.app.Application
import android.util.Log

data class Question(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: Int
)

data class Topic(
    val title: String,
    val shortDesc: String,
    val longDesc: String,
    val questions: List<Question>
)

interface TopicRepository {
    fun getTopics(): List<Topic>
}

class useTopicRepo: TopicRepository {
    override fun getTopics(): List<Topic> {
        val mathQuestions = listOf(
            Question("What is 4 x 6?", "27", "24", "50",
                "15", 2)
        )

        val scienceQuestions = listOf(
            Question("What is the process where plants make their own food using sunlight?",
                "Breathing", "Meditating", "Sleeping",
                "Photosynthesis", 4)
        )

        val marvelQuestions = listOf(
            Question("Which super hero character is from Marvel Studios?",
                "Power Rangers", "Wonder Woman", "Spider-Man",
                "Teenage Mutant Ninja Turtles", 3)
        )

        val topics = listOf(
            Topic("Math", "Math Overview",
                "This is a brief quiz that will test your knowledge on math. There is a total of 1 question.",
                mathQuestions),
            Topic("Science", "Science Overview",
                "This is a brief quiz that will test your knowledge on science. There is a total of 1 question.",
                scienceQuestions),
            Topic("Marvel Superheros", "Marvel Superheroes Overview",
                "This is a brief quiz that will test your knowledge on science. There is a total of 1 question.",
                marvelQuestions)
        )

        return topics
    }
}

class QuizApp : Application() {
    val topicRepository: TopicRepository = useTopicRepo()
    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp started")
    }
}