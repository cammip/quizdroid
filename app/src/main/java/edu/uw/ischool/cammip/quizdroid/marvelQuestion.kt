package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class marvelQuestion : AppCompatActivity() {
    private lateinit var topicRepository: TopicRepository
    private lateinit var subButton: Button
    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton
    private lateinit var radio4: RadioButton
    private lateinit var question: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_question)

        topicRepository = (application as QuizApp).topicRepository
        val topic = (topicRepository.getTopics())[2].questions[0]


        subButton = findViewById(R.id.subButton)
        radio1 = findViewById(R.id.radioButton1)
        radio2 = findViewById(R.id.radioButton2)
        radio3 = findViewById(R.id.radioButton3)
        radio4 = findViewById(R.id.radioButton4)
        question = findViewById(R.id.text3)

        question.text = topic.question
        radio1.text = topic.option1
        radio2.text = topic.option2
        radio3.text = topic.option3
        radio4.text = topic.option4

        subButton.setOnClickListener() {
            val selAnswer = getSelectedAnswer().toString()

            if (radio1.isChecked || radio2.isChecked || radio3.isChecked || radio4.isChecked) {
                startMathAnswer(selAnswer)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startMathAnswer(selectedAnswer: String) {
        val intent = Intent(this, marvelAnswer::class.java)
        // Pass the selected answer as an extra with the intent
        intent.putExtra("selectedAnswer", selectedAnswer)
        startActivity(intent)
    }

    private fun getSelectedAnswer(): String? {
        if (radio1.isChecked) {
            return radio1.text.toString()
        } else if (radio2.isChecked) {
            return radio2.text.toString()
        } else if (radio3.isChecked) {
            return radio3.text.toString()
        } else if (radio4.isChecked) {
            return radio4.text.toString()
        } else {
            return null
        }
    }
}