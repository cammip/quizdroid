package edu.uw.ischool.cammip.quizdroid
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var lstItems : ListView
    private lateinit var actionBar : Toolbar
    private lateinit var topicRepository: TopicRepository

    private fun isNetworkAvailable(connectivityManager: ConnectivityManager): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectivityManager =
            applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (isAirplaneModeOn(applicationContext)) {
            Log.d("CONNECT", "airplane mode is on")
        } else if (!isNetworkAvailable(connectivityManager)) {
                Log.d("CONNECT", "no internet connection")
        } else {
            Log.d("CONNECT", "connected")
        }

        lstItems = findViewById(R.id.lstItems)
        actionBar = findViewById(R.id.preferences)
        topicRepository = (application as QuizApp).topicRepository
        val titles = topicRepository.getTopics().map { it.title }

        actionBar.setOnClickListener() {
            navPreference()
        }

        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, titles)
        lstItems.adapter = adapter

        lstItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = titles[position]

            when (selectedItem) {
                titles[1] -> startScienceOverview()
                titles[0] -> startMathOverview()
                titles[2] -> startMarvelOverview()
            }
        }
    }

    private fun navPreference() {
        startActivity(Intent(this, Preferences::class.java))
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