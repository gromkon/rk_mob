package com.example.rk_mob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_course.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Long.parseLong
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class CourseActivity : AppCompatActivity() {

    companion object {
        const val COURSE_NAME = "BTS"
        const val VALUATE_NAME = "RUB"
        const val VALUATE_DAYS_COUNT = "5"
    }

    private var courseName = "BTS"
    private var valuateName = "RUB"
    private var valuateCount = "5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        val navController = findNavController(R.id.host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        if (intent.extras != null) {
            courseName = intent.getStringExtra(COURSE_NAME).toString()
            courseView.text = courseName
            valuateName = intent.getStringExtra(VALUATE_NAME).toString()
            valuateCount = intent.getStringExtra(VALUATE_DAYS_COUNT).toString()
        }

        val valuateCountInt = Integer.parseInt(valuateCount)
        val items = sendGet("https://min-api.cryptocompare.com/data/v2/histoday?fsym=$courseName&tsym=$valuateName&limit=" + (valuateCountInt - 1))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Adapter(items)
    }


    private fun sendGet(url: String): ArrayList<Item> {
        val url = URL(url)

        val s: StringBuilder = java.lang.StringBuilder("")
        thread(start = true) {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"
                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        s.append(line)
                    }
                }
            }
        }.join()

        var dataArray = JSONArray("[]")
        val answer = JSONObject(s.toString())
        for (key in answer.keys()) {
            if (key == "Data") {
                val answerData = JSONObject(answer.get(key).toString())
                for (keyData in answerData.keys()) {
                    if (keyData == "Data") {
                        dataArray = answerData.getJSONArray(keyData)
                    }
                }
            }
        }

        val items: ArrayList<Item> = ArrayList()
        for (i in 0 until dataArray.length()) {
            val item = dataArray.getJSONObject(i)
            items.add(Item(
                getDayBySeconds(item.getString("time")),
                item.getString("high"),
                valuateName,
                item.getString("low"),
                item.getString("open"),
                item.getString("close")
            ))
        }

        return items
    }

    private fun getDayBySeconds(time: String): String {
        val timeLong = parseLong(time + "000")
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(timeLong))
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.host_fragment)
        return navController.navigateUp()
    }
}