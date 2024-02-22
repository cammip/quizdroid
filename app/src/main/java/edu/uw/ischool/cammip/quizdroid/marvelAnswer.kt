package edu.uw.ischool.cammip.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent

class marvelAnswer : AppCompatActivity() {
    private lateinit var topicRepository: TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_answer)
        topicRepository = (application as QuizApp).topicRepository
        val topic = (topicRepository.getTopics())[2].questions[0]
        val questions = topic.answers

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