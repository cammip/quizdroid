package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class scienceAnswer : AppCompatActivity() {
    private lateinit var topicRepository: TopicRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_answer)

        topicRepository = (application as QuizApp).topicRepository
        val topic = (topicRepository.getTopics())[1].questions[0]
        val questions = listOf(topic.option1, topic.option2, topic.option3, topic.option4)

        val btn: Button = findViewById(R.id.finish)

        val selectedAnswer = intent.getStringExtra("selectedAnswer")
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val res = "Your Answer: " + selectedAnswer
        resultTextView.text = res

        val result: TextView = findViewById(R.id.textView3)
        val correct = "Correct answer: " + questions[topic.answer - 1]
        result.text = correct

        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}