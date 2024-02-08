package edu.uw.ischool.cammip.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    val values = listOf("Math", "Science", "Marvel Super Heroes")
    lateinit var lstItems : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lstItems = findViewById(R.id.lstItems)
        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values)
        lstItems.adapter = adapter

        lstItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = values[position]
            when (selectedItem) {
                "Math" -> startMathOverview()
                "Science" -> startScienceOverview()
                "Marvel Super Heroes" -> startMarvelOverview()
            }
        }
    }

    private fun startMathOverview() {
        startActivity(Intent(this, mathOverview::class.java))
    }

    private fun startScienceOverview() {
        startActivity(Intent(this, scienceOverview::class.java))
    }

    private fun startMarvelOverview() {
        startActivity(Intent(this, marvelOverview::class.java))
    }
}