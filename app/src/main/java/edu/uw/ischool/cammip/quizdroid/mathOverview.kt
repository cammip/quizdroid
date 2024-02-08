package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class mathOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_overview)

        val beginButton: Button = findViewById(R.id.beginButton)

        beginButton.setOnClickListener {
            val intent = Intent(this, mathQuestion::class.java)
            startActivity(intent)
        }
    }
}