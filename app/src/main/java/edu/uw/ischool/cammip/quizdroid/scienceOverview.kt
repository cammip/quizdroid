package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class scienceOverview : AppCompatActivity() {
    private lateinit var topicRepository: TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_overview)

        topicRepository = (application as QuizApp).topicRepository
        val topics = topicRepository.getTopics()

        val overviewTitle: TextView = findViewById(R.id.text1)
        overviewTitle.text = topics[1].title

        val desc: TextView = findViewById(R.id.text2)
        desc.text = topics[1].desc

        val begin: Button = findViewById(R.id.button2)

        begin.setOnClickListener {
            startActivity(Intent(this, scienceQuestion::class.java))
        }
    }
}