package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class mathOverview : AppCompatActivity() {
    private lateinit var topicRepository: TopicRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_overview)

        topicRepository = (application as QuizApp).topicRepository
        //list of all topics
        val topics = topicRepository.getTopics()

        val overviewTitle: TextView = findViewById(R.id.text1)
        overviewTitle.text = topics[0].title

        val desc: TextView = findViewById(R.id.text2)
        desc.text = topics[0].desc

        val beginButton: Button = findViewById(R.id.beginButton)

        beginButton.setOnClickListener {
            val intent = Intent(this, mathQuestion::class.java)
            startActivity(intent)
        }
    }
}