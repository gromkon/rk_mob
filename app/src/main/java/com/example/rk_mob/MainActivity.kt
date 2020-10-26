package com.example.rk_mob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val VALUATE_NAME = "RUB"
        const val VALUATE_DAYS_COUNT = "5"
    }

    private var valuateName = "RUB"
    private var valuateCount = "5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null) {
            valuateName = intent.getStringExtra(VALUATE_NAME).toString()
            valuateCount = intent.getStringExtra(VALUATE_DAYS_COUNT).toString()
        }

        buttonBTC.setOnClickListener {
            clickListener("BTC")
        }
        buttonETH.setOnClickListener {
            clickListener("ETH")
        }
        buttonXRP.setOnClickListener {
            clickListener("XRP")
        }
    }

    private fun clickListener(name: String) {
        val intent = Intent(this, CourseActivity::class.java)
        intent.putExtra(CourseActivity.COURSE_NAME, name)
        intent.putExtra(CourseActivity.VALUATE_NAME, valuateName)
        intent.putExtra(CourseActivity.VALUATE_DAYS_COUNT, valuateCount)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra(SettingsActivity.VALUATE_NAME, valuateName)
        intent.putExtra(SettingsActivity.VALUATE_DAYS_COUNT, valuateCount)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}