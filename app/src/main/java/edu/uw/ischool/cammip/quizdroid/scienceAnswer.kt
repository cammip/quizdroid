package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class scienceAnswer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_answer)

        val btn: Button = findViewById(R.id.finish)

        val selectedAnswer = intent.getStringExtra("selectedAnswer")
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val res = "Your Answer: " + selectedAnswer
        resultTextView.text = res

        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}