package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class marvelOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_overview)

        val begin: Button = findViewById(R.id.button2)

        begin.setOnClickListener {
            startActivity(Intent(this, marvelQuestion::class.java))
        }
    }
}